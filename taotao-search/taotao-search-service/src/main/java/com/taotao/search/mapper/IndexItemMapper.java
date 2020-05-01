package com.taotao.search.mapper;

import java.util.List;

import com.taotao.common.pojo.SearchItem;

/**
 * 商品数据导入索引库Mapper接口
 * @author zhaoyuan
 * @date 2019年4月14日 下午8:24:14
 */
public interface IndexItemMapper {

	/**
	 * 查询用户搜索商品页面显示的属性
	 * @return
	 */
	List<SearchItem> getItemList();
	
	/**
	 * 由商品Id查询搜索工程要展示的商品信息
	 * @param itemId
	 * @return
	 */
	SearchItem getItemById(long itemId);
	
}
