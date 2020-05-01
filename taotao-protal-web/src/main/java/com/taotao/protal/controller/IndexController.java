package com.taotao.protal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.protal.pojo.AD1Node;
import com.taotao.utils.JsonUtils;

/**
 * 首页跳转Controller
 * @author zhaoyuan
 * @date 2019-03-01 10:39 PM
 */
@Controller
public class IndexController {

	//轮廓图显示图片的尺寸
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIGHT}")
	private Integer AD1_WIGHT;
	@Value("${AD1_WIGHT_B}")
	private Integer AD1_WIGHT_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 门户跳转首页
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model){
		List<AD1Node> ad1Nodelist = new ArrayList<>();
		//根据分类Id查询轮廓图列表
		List<TbContent> contents = contentService.getContentListByCid(AD1_CATEGORY_ID);
		for (TbContent content : contents) {
			AD1Node ad1Node = new AD1Node();
			ad1Node.setAlt(content.getTitle());
			ad1Node.setHeight(AD1_HEIGHT);
			ad1Node.setHeightB(AD1_HEIGHT_B);
			ad1Node.setWidth(AD1_WIGHT);
			ad1Node.setWidthB(AD1_WIGHT_B);
			ad1Node.setSrc(content.getPic());
			ad1Node.setSrcB(content.getPic2());
			ad1Node.setHref(content.getUrl());;
			ad1Nodelist.add(ad1Node);
		}
		//将列表转换为json数据
		String ad1Result = JsonUtils.objectToJson(ad1Nodelist);
		//填充模型
		model.addAttribute("ad1", ad1Result);
		
		return "index";
	}
	
}
