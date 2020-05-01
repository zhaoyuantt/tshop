package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

/**
 * 内容分类管理Controller
 * @author zhaoyuan
 * @date 2019年3月2日 下午2:50:16
 */
@Controller
@RequestMapping("/content")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 内容分类列表查询
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/category/list")
	@ResponseBody
	public Object getContentCategory(@RequestParam(value="id", defaultValue="0")Long parentId){
		List<EasyUITreeNode> resultList = contentCategoryService.getContentCategory(parentId);
		return  resultList;
	}
	
	/**
	 * 内容分类添加
	 * @param parentId
	 * @param text
	 * @return
	 */
	@RequestMapping("/category/create")
	@ResponseBody
	public Object addContentCategory(Long parentId,String name){
		TaotaoResult result = contentCategoryService.addContentCategory(name, parentId);
		return result;
	}
	
}
