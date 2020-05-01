package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

/**
 * 商品内容管理Controller
 * @author zhaoyuan
 * @date 2019年3月2日 下午6:55:34
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 内容添加
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
		TaotaoResult result = contentService.addContent(content);
		return  result;
	}
	
	/**
	 * 内容列表查询
	 * @param pageNum
	 * @param pageSize
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/query/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult getContentList(@RequestParam(value="page")int pageNum,
			@RequestParam(value="rows")int pageSize,long categoryId){
		EasyUIDataGridResult result = contentService.getContentList(pageNum, pageSize, categoryId);
		return result;
	}
	
}
