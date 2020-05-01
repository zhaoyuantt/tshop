package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品描述service接口
 * 
 * @author zhaoyuan
 * @date 2019年11月22日 上午9:43:58
 */
public interface ItemDescService {

	/**
	 * 有商品Id查询商品描述
	 * @param itemId
	 * @return
	 */
	public TaotaoResult getItemDescByItemId(long itemId);
}
