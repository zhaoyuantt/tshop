package com.taotao.mymapper;

import java.util.List;

import com.taotao.common.pojo.ItemParamInfo;


/**
 * 商品规格参数dao
 * @author zhaoyuan
 * @date 2019年5月13日 下午4:22:38
 */
public interface ItemParamMapper {
	
	/**
	 * 商品规格参数初始化
	 * @return
	 */
	public List<ItemParamInfo> getItemParamList();
}
