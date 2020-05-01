package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.ItemParamInfo;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.mymapper.ItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamService;
import com.taotao.utils.JsonUtils;

/**
 * 商品规格参数接口实现
 * @author zhaoyuan
 * @date 2019年5月13日 下午2:15:35
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	private static final Logger log = Logger.getLogger(ItemParamServiceImpl.class);

	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private ItemParamMapper myItemParamMapper;
	
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${ITEM_EXPIRE}")
	private Integer ITEM_EXPIRE;
	@Value("${PARAM}")
	private String PARAM;
	
	@Override
	public EasyUIDataGridResult getItemParamList(int pageNum, int pageSize) {
		//设置分页条件
		PageHelper.startPage(pageNum, pageSize);
		
		//执行查询
		//TbItemParamExample example = new TbItemParamExample();
		//List<TbItemParam> itemParamList = itemParamMapper.selectByExample(example);
		List<ItemParamInfo> itemParamList = myItemParamMapper.getItemParamList();
		
		//取查询结果
		PageInfo<ItemParamInfo> pageInfo = new PageInfo<>(itemParamList);
		
		//处理返回结果,发布服务
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemParamList);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult getItemParamGroupByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//List<TbItemParam> itemParamList = itemParamMapper.selectByExample(example);
		List<TbItemParam> itemParamList = itemParamMapper.selectByExampleWithBLOBs(example);
		if(null != itemParamList && itemParamList.size()>0){
			return TaotaoResult.ok(itemParamList.get(0));
		}else{
			return TaotaoResult.ok();
		}
		
	}

	@Override
	public TaotaoResult insertItemParam(long cid, String paramData) {
		//构造插入对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		//执行插入
		itemParamMapper.insert(itemParam);
		
		//返回结果
		return TaotaoResult.ok();
	}

	@Override
	public String getItemParamItem(long itemId) {
		try {
			//查询缓存
			String paramDataRedis = jedisClient.get(ITEM_INFO+":"+itemId+":"+PARAM);
			if(StringUtils.isNotBlank(paramDataRedis)){
				log.info("商品："+itemId+ " 规格参数缓存命中");
				return paramDataRedis;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		//根据商品Id查询改商品的规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> itemParamItemList = itemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if(null == itemParamItemList || itemParamItemList.size() == 0){
			return "";
		}
		
		//取规格参数
		TbItemParamItem itemParamItem = itemParamItemList.get(0);
		String paramData = itemParamItem.getParamData();
		
		//生成html片段
		StringBuffer sb = new StringBuffer();
		//把规格参数转换为List
		List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
		
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n" );
		sb.append("	<tbody>\n" );
		
		for (Map map : paramList) {
			//处理规格参数的组
			sb.append("		<tr>\n" );
			sb.append("			<th class=\"tbTitle\" colspan=\"2\" align=\"center\">"+map.get("group")+"</th>\n" );
			sb.append("		</tr>\n" );
			
			//处理组中的参数
			List<Map> paramMap = (List<Map>) map.get("params");
			for (Map map2 : paramMap) {
				sb.append("		<tr>\n" );
				sb.append("			<td class=\"tbTitle\">"+map2.get("k")+"</td>\n" );
				sb.append("			<td>"+map2.get("v")+"</td>\n" );
				sb.append("		</tr>\n" );
				
			}
		}
		sb.append("	</tbody>\n" );
		sb.append("</table>");
		
		try {
			//添加缓存
			jedisClient.set(ITEM_INFO+":"+itemId+":"+PARAM, sb.toString());
			jedisClient.expire(ITEM_INFO+":"+itemId+":"+PARAM, ITEM_EXPIRE);
			log.info("商品："+itemId+ " 添加规格参数缓存");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回
		return sb.toString();
	}

	@Override
	public TaotaoResult getItemParamItemByItemId(long itemId) {
		TbItemParamItemExample itemParamItemExample = new 
				TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria itemParamItemCriteria = 
				itemParamItemExample.createCriteria();
		itemParamItemCriteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> itemParamItemList = itemParamItemMapper.selectByExampleWithBLOBs(itemParamItemExample);
		
		if(null != itemParamItemList && 0 < itemParamItemList.size()){
			TbItemParamItem itemParamItem = itemParamItemList.get(0);
			return TaotaoResult.ok(itemParamItem);
		}
		return TaotaoResult.build(400, "没有查询到规格参数信息，itemId为："+itemId);
	}

}
