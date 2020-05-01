package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemDescService;

/**
 * 商品描述Controller
 * 
 * @author zhaoyuan
 * @date 2019年11月22日 上午10:02:34
 */
@Controller
@RequestMapping("/item")
public class ItemDescController {

	@Autowired
	private ItemDescService itemDescService;
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDescByItemId(@PathVariable long itemId){
		try {
			TaotaoResult result = itemDescService.getItemDescByItemId(itemId);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
}
