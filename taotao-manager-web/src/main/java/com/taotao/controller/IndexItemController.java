package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.IndexItemService;

/**
 * 商品数据导入索引库Controller
 * @author zhaoyuan
 * @date 2019年4月25日 下午6:08:07
 */
@Controller
@RequestMapping("/index")
public class IndexItemController {
	
	@Autowired
	private IndexItemService indexItemService;
	
	/**
	 * 商品数据导入索引库
	 * @return
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItemToIndexdb(){
		try {
			TaotaoResult result = indexItemService.importItemsToIndex();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "商品数据导入索引库失败");
		}
	}

}
