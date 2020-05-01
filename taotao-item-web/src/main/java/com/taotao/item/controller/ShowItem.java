package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.item.pojo.ItemCustom;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

/**
 * 商品详情页面展示Controller
 * @author zhaoyuan
 * @date 2019年5月1日 下午4:32:41
 */
@Controller
public class ShowItem {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/{itemId}")
	//@ResponseBody
	public String visitItem(@PathVariable long itemId,Model model){
		TbItem item = itemService.getItemById(itemId);
		TbItemDesc itemDesc = itemService.getItemDescByItemId(itemId);
		String paramData = itemParamService.getItemParamItem(itemId);
		ItemCustom itemCustom = new ItemCustom(item);
		model.addAttribute("item", itemCustom);
		model.addAttribute("itemDesc", itemDesc);
		model.addAttribute("itemParam", paramData);
		
		return "item";
	}

}
