package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbAdminUserItemMapper;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mymapper.AdminUserItemMapper;
import com.taotao.pojo.TbAdminUserItem;
import com.taotao.pojo.TbAdminUserItemExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import com.taotao.utils.JsonUtils;

/**
 * 商品服务的接口实现
 * 
 * @author zhaoyuan
 * @date 2019-02-17 11:12 AM
 */
@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger log = Logger.getLogger(ItemServiceImpl.class);

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private AdminUserItemMapper myAdminUserItemMapper;
	@Autowired
	private TbAdminUserItemMapper adminUserItemMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name = "itemAddtopic") // 默认是根据Id注入的
	private Destination destination;

	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;
	@Value("${BACE}")
	private String BACE;
	@Value("${DESC}")
	private String DESC;

	@Override
	public TbItem getItemById(long itemId) {

		// ADD BY ZHAOYUAN 0N 2019-05-01 5:38 PM
		try {
			// 查询缓存
			String redisItemJson = jedisClient.get(ITEM_INFO + ":" + itemId + ":" + BACE);
			if (StringUtils.isNotBlank(redisItemJson)) {
				TbItem redisItem = JsonUtils.jsonToPojo(redisItemJson, TbItem.class);
				if (log.isDebugEnabled()) {
					log.debug("商品缓存命中： " + itemId);
				}
				return redisItem;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// END ADD

		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		// ADD BY ZHAOYUAN 0N 2019-05-01 5:38 PM
		try {
			// 添加缓存
			jedisClient.set(ITEM_INFO + ":" + itemId + ":" + BACE, JsonUtils.objectToJson(item));
			// 添加缓存过期时间
			jedisClient.expire(ITEM_INFO + ":" + itemId + ":" + BACE, ITEM_EXPIRE);
			if (log.isDebugEnabled()) {
				log.debug("商品添加缓存： " + itemId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// END ADD

		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize) {
		// 设置查询条件--分页信息
		PageHelper.startPage(pageNum, pageSize);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		// 返回结果
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item, StringBuffer desc, String paramData) {
		// 生成商品Id
		final long itemId = IDUtils.genItemId();
		// 商品信息
		// 补全item属性
		item.setId(itemId);
		item.setStatus((byte) 1); // 1-正常 2-下架 3-删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 向数据库插入
		itemMapper.insert(item);

		// 商品描述信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc.toString());
		// 向商品内容表中插入数据
		itemDescMapper.insert(itemDesc);

		// 商品规格参数信息
		// ADD ON 2019-05-14 3:27 PM
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 插入数据
		itemParamItemMapper.insert(itemParamItem);

		// ***************************************************
		// ADD BY ZHAOYUAN ON 2019-04-29 2:31 PM
		// 向ActiveMQ发送一个商品添加索引的数据
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 此处需要发送商品的Id
				TextMessage textMessage = session.createTextMessage(itemId + "");
				System.out.println("************************************");
				System.out.println("添加商品向ActiveMQ发送商品Id：" + itemId);
				System.out.println("************************************");
				return textMessage;
			}
		});
		// END ADD
		// ***************************************************

		return TaotaoResult.ok();
	}

	@Override
	public TbItemDesc getItemDescByItemId(long itemId) {

		try {
			// 查询缓存
			String redisItemDescJson = jedisClient.get(ITEM_INFO + ":" + itemId + ":" + DESC);
			if (StringUtils.isNotBlank(redisItemDescJson)) {
				TbItemDesc redisItemDesc = JsonUtils.jsonToPojo(redisItemDescJson, TbItemDesc.class);
				if (log.isDebugEnabled()) {
					log.debug("商品描述缓存命中： " + itemId);
				}
				return redisItemDesc;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		try {
			// 添加缓存
			jedisClient.set(ITEM_INFO + ":" + itemId + ":" + DESC, JsonUtils.objectToJson(itemDesc));
			// 添加缓存过期时间
			jedisClient.expire(ITEM_INFO + ":" + itemId + ":" + DESC, ITEM_EXPIRE);
			if (log.isDebugEnabled()) {
				log.debug("商品描述添加缓存： " + itemId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemDesc;
	}

	@Override
	public TaotaoResult addItem(TbItem item, StringBuffer desc, String paramData, String adminUserId) {
		// 生成商品Id
		final long itemId = IDUtils.genItemId();

		// 商品信息
		// 补全item属性
		item.setId(itemId);
		item.setStatus((byte) 1); // 1-正常 2-下架 3-删除
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		//类目名称
		//Add on 2019-11-22 23:11:10
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(item.getCid());
		item.setCname(itemCat.getName());
		//End add
		
		// 向数据库插入
		itemMapper.insert(item);

		// 商品描述信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc.toString());
		// 向商品内容表中插入数据
		itemDescMapper.insert(itemDesc);

		// 商品规格参数信息
		// ADD ON 2019-05-14 3:27 PM
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 插入数据
		itemParamItemMapper.insert(itemParamItem);

		// Add on 2019-11-21 16:49:54
		// 商品与用户的关联关系
		TbAdminUserItem adminUserItem = new TbAdminUserItem();
		String id = IDUtils.getGeneId();
		adminUserItem.setId(id);
		adminUserItem.setItemId(itemId);
		adminUserItem.setUseradminId(adminUserId);
		adminUserItem.setCreated(new Date());
		adminUserItem.setUpdated(new Date());
		// 插入数据
		adminUserItemMapper.insert(adminUserItem);
		// End add

		// ***************************************************
		// ADD BY ZHAOYUAN ON 2019-04-29 2:31 PM
		// 向ActiveMQ发送一个商品添加索引的数据
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 此处需要发送商品的Id
				TextMessage textMessage = session.createTextMessage(itemId + "");
				System.out.println("************************************");
				System.out.println("添加商品向ActiveMQ发送商品Id：" + itemId);
				System.out.println("************************************");
				return textMessage;
			}
		});
		// END ADD
		// ***************************************************

		return TaotaoResult.ok();
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, String adminUserId) {
		// 设置查询条件--分页信息
		PageHelper.startPage(pageNum, pageSize);

		// 执行查询
		// TbItemExample example = new TbItemExample();
		// List<TbItem> list = itemMapper.selectByExample(example);
		List<TbItem> itemWithAdminUserList = myAdminUserItemMapper.getItemListByUserAdminId(adminUserId);

		// 取查询结果
		// PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemWithAdminUserList);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemWithAdminUserList);
		result.setTotal(pageInfo.getTotal());

		// 返回结果
		return result;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, TbItem item) {
		// 设置查询条件--分页信息
		PageHelper.startPage(pageNum, pageSize);

		// 设置查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		if (null != item.getTitle() && !"".equals(item.getTitle())) {
			criteria.andTitleLike("%" + item.getTitle() + "%");
		}
		if (null != item.getSellPoint() && !"".equals(item.getSellPoint())) {
			criteria.andSellPointLike("%" + item.getSellPoint() + "%");
		}

		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);

		// 取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());

		// 返回结果
		return result;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer pageNum, Integer pageSize, String adminUserId, TbItem item) {
		// 设置查询条件--分页信息
		PageHelper.startPage(pageNum, pageSize);

		// 执行查询
		// TbItemExample example = new TbItemExample();
		// List<TbItem> list = itemMapper.selectByExample(example);
		List<TbItem> itemWithAdminUserList = myAdminUserItemMapper.getItemListByUserAdminIdWithCondition(adminUserId,
				item);

		// 取查询结果
		// PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemWithAdminUserList);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemWithAdminUserList);
		result.setTotal(pageInfo.getTotal());

		// 返回结果
		return result;
	}

	@Override
	public TaotaoResult itemDownOrUpByItemIds(int operType, String itemIds) {

		long[] itemIdLongAry = getItemIds_long_(itemIds);

		// 下架
		if (2 == operType) {
			for (long itemId : itemIdLongAry) {
				TbItem item = itemMapper.selectByPrimaryKey(itemId);
				byte i = 2;
				item.setStatus(i);
				itemMapper.updateByPrimaryKey(item);

				// 删除缓存
				changeItemRedisCacheByItemId(itemId);
			}
			return TaotaoResult.ok();
		}

		// 上架
		if (1 == operType) {
			for (long itemId : itemIdLongAry) {
				TbItem item = itemMapper.selectByPrimaryKey(itemId);
				byte i = 1;
				item.setStatus(i);
				itemMapper.updateByPrimaryKey(item);
			}
			return TaotaoResult.ok();
		}

		return TaotaoResult.build(400, "无效操作类型");
	}

	@Override
	public TaotaoResult deleteItemByItemIds(String itemIds) {
		// 获得商品id数组
		long[] itemIdLongAry = getItemIds_long_(itemIds);

		for (int i = 0; i < itemIdLongAry.length; i++) {
			long itemId = itemIdLongAry[i];

			// 删除商品基本信息
			itemMapper.deleteByPrimaryKey(itemId);

			// 删除商品描述信息
			itemDescMapper.deleteByPrimaryKey(itemId);

			// 删除商品规格参数信息
			// itemParamItemMapper.deleteByPrimaryKey(itemId);
			TbItemParamItemExample example_p = new TbItemParamItemExample();
			com.taotao.pojo.TbItemParamItemExample.Criteria criteria_p = example_p.createCriteria();
			criteria_p.andItemIdEqualTo(itemId);
			itemParamItemMapper.deleteByExample(example_p);

			// 删除商品关联的用户信息
			// adminUserItemMapper.deleteByPrimaryKey(itemId);
			TbAdminUserItemExample example_u = new TbAdminUserItemExample();
			com.taotao.pojo.TbAdminUserItemExample.Criteria criteria_u = example_u.createCriteria();
			criteria_u.andItemIdEqualTo(itemId);
			adminUserItemMapper.deleteByExample(example_u);

			// 删除商品缓存
			changeItemRedisCacheByItemId(itemId);

		}

		return TaotaoResult.ok();
	}

	// *******************************************************
	/**
	 * 删除商品缓存信息
	 * 
	 * @date 2019-11-21 11:28:49
	 * @param itetmId
	 */
	public void changeItemRedisCacheByItemId(long itemId) {
		try {
			jedisClient.del(ITEM_INFO + ":" + itemId + ":" + BACE);
		} catch (Exception e) {
			log.error("删除商品缓存出错，id为：" + itemId);
			e.printStackTrace();
		}
	}

	/**
	 * 将字符串转为long类型的数组
	 * 
	 * @date 2019-11-21 17:17:00
	 * @param itemIds
	 * @return
	 */
	long[] getItemIds_long_(String itemIds) {
		// 处理参数
		String[] itemIdArray = itemIds.split(",");

		// 定义Long类型的数组
		long[] itemIdLongAry = new long[itemIdArray.length];

		for (int i = 0; i < itemIdLongAry.length; i++) {
			itemIdLongAry[i] = new Long(itemIdArray[i]);
		}

		return itemIdLongAry;
	}

	@Override
	public TaotaoResult updataItem(TbItem item, StringBuffer desc, String paramData) {
		//判断商品Id是否为空
		if(null == item.getId()){
			return TaotaoResult.build(400, "编辑商品时商品Id为空");
		}
		
		//商品信息
		TbItem itemSFY = itemMapper.selectByPrimaryKey(item.getId());
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(item.getCid());
		item.setCname(itemCat.getName());
		item.setUpdated(new Date());
		item.setStatus(itemSFY.getStatus());
		item.setCreated(itemSFY.getCreated());
		itemMapper.updateByPrimaryKey(item);
		
		//商品描述信息
		if(null != desc){
			TbItemDescExample itemDescExample = new TbItemDescExample();
			com.taotao.pojo.TbItemDescExample.Criteria itemDescCriteria = itemDescExample.createCriteria();
			itemDescCriteria.andItemIdEqualTo(item.getId());
			List<TbItemDesc> itemDescList = itemDescMapper.selectByExampleWithBLOBs(itemDescExample);
			
			if(null != itemDescList && 0 < itemDescList.size()){
				TbItemDesc itemDesc = itemDescList.get(0);
				itemDesc.setItemDesc(desc.toString());
				itemDesc.setUpdated(new Date());
				
				itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
			}
		}
		
		//商品规格参数
		if(null != paramData){
			TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
			com.taotao.pojo.TbItemParamItemExample.Criteria itemParamItemCriteria = itemParamItemExample.createCriteria();
			itemParamItemCriteria.andItemIdEqualTo(item.getId());
			List<TbItemParamItem> itemParamItemList = itemParamItemMapper.selectByExampleWithBLOBs(itemParamItemExample);
			
			if(null != itemParamItemList && 0 < itemParamItemList.size()){
				TbItemParamItem itemParamItem = itemParamItemList.get(0);
				itemParamItem.setParamData(paramData);
				itemParamItem.setUpdated(new Date());
				
				itemParamItemMapper.updateByPrimaryKeyWithBLOBs(itemParamItem);
				
			}
		}
		
		return TaotaoResult.ok();
	}

}
