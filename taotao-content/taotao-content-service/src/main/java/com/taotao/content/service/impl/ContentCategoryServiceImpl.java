package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

/**
 * 商品内容管理接口实现
 * @author zhaoyuan
 * @date 2019年3月2日 下午2:22:14
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper conontentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategory(Long parentId) {
		//根据父节点Id查询叶子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		List<TbContentCategory> contentCategories = conontentCategoryMapper.selectByExample(example);
		for (TbContentCategory contentCategory : contentCategories) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			//如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(contentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult addContentCategory(String name, Long parentId) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setUpdated(new Date());
		contentCategory.setCreated(new Date());
		//1-正常   2-删除
		contentCategory.setStatus(1);
		//默认为1
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		conontentCategoryMapper.insert(contentCategory);
		//判断父节点的状态,新增的节点一定为叶子节点，如果他的父节点不是父节点，需要改为父节点
		TbContentCategory tbContentCategory = conontentCategoryMapper.selectByPrimaryKey(parentId);
		if(!tbContentCategory.getIsParent()){
			tbContentCategory.setIsParent(true);
			conontentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		}
		return TaotaoResult.ok(contentCategory);
	}

}
