package com.taotao.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.content.pojo.CategoryResult;
import com.taotao.content.service.ItemCategoryService;
import com.taotao.utils.JsonUtils;

/**
 * 商品分类Controller
 * @author zhaoyuan
 * @date 2019年5月15日 下午3:38:18
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCategoryController {

	@Autowired
	private ItemCategoryService itemCategoryService;
	
	/**
	 * 获得所有商品分类
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getItemCategoryList(String callback){
		CategoryResult categoryResult = itemCategoryService.getItemCategoryList();
		String json = JsonUtils.objectToJson(categoryResult);
		String result = callback + "(" + json + ");";
		return result;
	}
	
}
