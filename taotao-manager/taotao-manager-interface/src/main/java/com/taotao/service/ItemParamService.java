package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格参数接口
 * @author zhaoyuan
 * @date 2019年5月13日 下午2:07:57
 */
public interface ItemParamService {

	/**
	 * 商品规格参数初始化
	 * @param pageNum   多少页
	 * @param pageSize  每页显示多少条数据
	 * @return
	 */
	EasyUIDataGridResult getItemParamList(int pageNum,int pageSize);
	
	/**
	 * 根据商品分类Id查询分类规格分组
	 * @param cid   商品分类Id
	 * @return
	 */
	TaotaoResult getItemParamGroupByCid(long cid);
	
	/**
	 * 商品规格参数分组添加
	 * @param cid
	 * @param paramData
	 * @return
	 */
	TaotaoResult insertItemParam(long cid,String paramData);
	
	/**
	 * 取商品规格参数
	 * @param itemId   商品Id
	 * @return
	 */
	String getItemParamItem(long itemId);
	
	/**
	 * 由商品Id获得商品规格参数
	 * 
	 * @date 2019-11-22 10:36:10
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParamItemByItemId(long itemId);
	
}
