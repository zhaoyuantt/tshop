package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.service.ItemParamService;

/**
 * 测试商品规格参数生成Html页面
 * @author zhaoyuan
 * @date 2019年5月15日 上午9:14:28
 */
@Controller
public class ItemParamGeneHtml {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping(value="/item/showparam/{itemId}",method=RequestMethod.GET)
	public String itemParamGenHtml(@PathVariable long itemId,Model model){
		String paramDate = itemParamService.getItemParamItem(itemId);
		model.addAttribute("paramDate", paramDate);
		return "showItem";
	}
	
}
