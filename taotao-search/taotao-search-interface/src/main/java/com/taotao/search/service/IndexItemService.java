package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品索引管理Service接口
 * @author zhaoyuan
 * @date 2019年4月14日 下午9:24:54
 */
public interface IndexItemService {

	/**
	 * 商品数据导入索引库
	 * @return
	 */
	TaotaoResult importItemsToIndex();

	/**
	 * 单个商品导入索引库
	 * @param itemId
	 */
	void addDocument(long itemId);
	
}
