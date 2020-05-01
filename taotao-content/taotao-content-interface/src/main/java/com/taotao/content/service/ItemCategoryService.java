package com.taotao.content.service;

import com.taotao.content.pojo.CategoryResult;

/**
 * 门户首页商品分类接口
 * @author zhaoyuan
 * @date 2019年5月15日 下午2:07:33
 */
public interface ItemCategoryService {

	/**
	 * 首页商品分类展示
	 * @return
	 */
	CategoryResult getItemCategoryList();
}
