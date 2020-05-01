package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemCatService;

/**
 * 商品类目的Controller
 * @author zhaoyuan
 * @date 2019-02-22 10：56 PM
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 商品类目列表查询
	 * @param parentId   节点父Id
	 * @return Object   返回List<EasyUITreeNode>会报错，目前没找到原因
	 */
	/*@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}*/
	
	@RequestMapping("/list")
	@ResponseBody
	public Object getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		//return JsonUtils.objectToJson(list);
		return list;
	}
	
	/**
	 * 2019-05-08 10:55 AM
	 * 商品类目添加
	 * @param name
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItemCategory(String name,long parentId){
		TaotaoResult result = itemCatService.createItemCategory(name, parentId);
		return result;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItemCategoryByCid(@RequestParam(value="id")long cid,String name){
		try {
			TaotaoResult result = itemCatService.updateItemCategoryByCid(name, cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItemCategoryByCid(@RequestParam(value="id")long cid){
		try {
			long parentId = 520;
			TaotaoResult result = itemCatService.deleteItemCategoryByCid(cid, parentId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		
	}
	
	/**
	 * 有类目Id查询类目名称
	 * @param cid
	 * @return
	 */
	@RequestMapping("/cname/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatNameByCid(@PathVariable long cid){
		try {
			TaotaoResult result = itemCatService.getItemCatNameByCid(cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
}
