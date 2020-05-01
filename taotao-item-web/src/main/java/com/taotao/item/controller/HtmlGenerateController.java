package com.taotao.item.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.item.pojo.Goddess;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * Spring 整合Freemarker测试类
 * @author zhaoyuan
 * @date 2019年5月2日 下午12:54:59
 */
@Controller
public class HtmlGenerateController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("/genHtml")
	@ResponseBody
	public String generateHtml(){
		Writer out = null;
		try {
			//从FreeMarkerConfigurer对象中获得Configuration对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			//加载模板文件
			Template template = configuration.getTemplate("goddessList.ftl");
			//数据集
			ArrayList<Goddess> goddessList = new ArrayList<>();
			Goddess goddessS = new Goddess(1, "索菲亚", "南京",new Date());
			Goddess goddessD = new Goddess(1, "邓紫棋", "香港",new Date(),"歌手");
			goddessList.add(goddessS);
			goddessList.add(goddessD);
			Map<Object, ArrayList<Goddess>> map = new HashMap<>();
			map.put("goddessList", goddessList);
			//输出文件路径和文件名
			out = new FileWriter("E:/temp/outhtml/goddessList.html");
			//输出文件
			template.process(map, out);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
			return "TemplateNotFoundException";
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
			return "MalformedTemplateNameException";
		} catch (ParseException e) {
			e.printStackTrace();
			return "ParseException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		} catch (TemplateException e) {
			e.printStackTrace();
			return "TemplateException";
		} finally {
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "OK";
	}
	
}
