package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品类目的Service
 * @author zhaoyuan
 * @Date 2019-02-22 10:29 PM
 */
public interface ItemCatService {

	/**
	 * 商品类目列表查询
	 * @param parentId   父节点Id
	 * @return   List<EasyUITreeNode>
	 */
	List<EasyUITreeNode> getItemCatList(long parentId);
	
	/**
	 * 2019-05-08 10:10 AM
	 * 商品类目添加
	 * @param name
	 * @param parentId
	 * @return
	 */
	TaotaoResult createItemCategory(String name,long parentId);
	
	/**
	 * 2019-05-08 11:09 AM
	 * 商品类目重命名
	 * @param name
	 * @param cid
	 * @return
	 */
	TaotaoResult updateItemCategoryByCid(String name,long cid);
	
	/**
	 * 2019-05-08 1:52 PM
	 * 商品类目删除
	 * 同时删除该类目下的所有叶子节点
	 * @param cid
	 * @param parentId
	 * @return
	 */
	TaotaoResult deleteItemCategoryByCid(long cid,long parentId);
	
	/**
	 * 商品类目列表查询-根据cid使parentId类目节点状态改为open
	 * @date 2019-11-22 14:08:01
	 * @param parentId  父节点Id
	 * @param cid   当前类目Id
	 * @return
	 */
	List<EasyUITreeNode> getItemCatList(long parentId,long cid);
	
	/**
	 * 有类目Id查询类目名称
	 * @date 2019-11-22 17:33:13
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemCatNameByCid(long cid);
	
}
