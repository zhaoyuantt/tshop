package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.content.pojo.CategoryNode;
import com.taotao.content.pojo.CategoryResult;
import com.taotao.content.service.ItemCategoryService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.utils.JsonUtils;

/**
 * 门户首页商品分类接口
 * @author zhaoyuan
 * @date 2019年5月15日 下午2:12:29
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	private static final Logger log = Logger.getLogger(ItemCategoryServiceImpl.class);
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Value("${INDEX_ITEM_CATEGORY}")
	private String INDEX_ITEM_CATEGORY;
	
	@Override
	public CategoryResult getItemCategoryList() {
		CategoryResult categoryResult = new CategoryResult();
		
		try {
			//查询缓存
			String catDateRedis = jedisClient.get(INDEX_ITEM_CATEGORY);
			if(StringUtils.isNotBlank(catDateRedis)){
				List<Object> catResultList = JsonUtils.jsonToList(catDateRedis, Object.class);
				categoryResult.setData(catResultList);
				log.info("门户首页商品分类缓存命中");
				return categoryResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		categoryResult.setData(getItemCatList(0));
		return categoryResult;
	}
	
	
	/**
	 * 递归查询商品分类
	 * @param parentId
	 * @return
	 */
	List<?> getItemCatList(long parentId){
		//先查询 parent_id 为0的
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
		
		List<Object> categoryResult = new ArrayList<>();
		
		int count = 0;
		
		for (TbItemCat itemCat : itemCatList) {
			//判断是否为父节点
			if(itemCat.getIsParent()){
				CategoryNode categoryNode = new CategoryNode();
				
				if(0 == itemCat.getParentId()){
					categoryNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				}else{
					categoryNode.setName(itemCat.getName());
				}
				
				categoryNode.setUrl("/products/"+itemCat.getId()+".html");
				categoryNode.setCat(getItemCatList(itemCat.getId()));   //开始递归
				
				categoryResult.add(categoryNode);
				
				count ++;
				//第一层只取14条记录
				if (parentId == 0 && count >=14) {
					break;
				}
			}else{
				categoryResult.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
		}
		
		//添加缓存
		try {
			jedisClient.set(INDEX_ITEM_CATEGORY, JsonUtils.objectToJson(categoryResult));
			log.info("门户首页添加商品分类缓存");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryResult;
	}

}
