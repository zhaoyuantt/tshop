package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数Controller
 * @author zhaoyuan
 * @date 2019-05-13 2:39 PM
 */
@Controller
@RequestMapping("/item")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 商品规格参数初始化
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/param/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(@RequestParam(value="page",defaultValue="1") int pageNum,
			@RequestParam(value="rows",defaultValue="30")int pageSize){
		EasyUIDataGridResult result = itemParamService.getItemParamList(pageNum, pageSize);
		return result;
	}
	
	/**
	 * 根据商品分类Id查询分类规格分组
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/param/query/itemcatid/{cid}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult getItemParamGroupByCid(@PathVariable long cid){
		TaotaoResult result = itemParamService.getItemParamGroupByCid(cid);
		return result;
	}
	
	/**
	 * 商品规格参数分组添加
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value="/param/save/{cid}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable long cid,String paramData){
		try {
			TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * 由商品Id查询商品规格参数
	 * 
	 * @date 2019-11-22 10:51:12
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamItemByItemId(@PathVariable long itemId){
		try {
			TaotaoResult result = itemParamService.getItemParamItemByItemId(itemId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage(), null);
		}
	}
}
