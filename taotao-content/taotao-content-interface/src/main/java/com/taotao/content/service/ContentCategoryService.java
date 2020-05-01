package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品内容分类管理接口
 * @author zhaoyuan
 * @date 2019-03-01 2:18 PM
 */
public interface ContentCategoryService {

	/**
	 * 内容分类列表查询
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategory(Long parentId);
	
	/**
	 * 内容分类添加
	 * @param name   分类名称
	 * @param parentId   父节点ID
	 * @return
	 */
	public TaotaoResult addContentCategory(String name,Long parentId);
	
}
