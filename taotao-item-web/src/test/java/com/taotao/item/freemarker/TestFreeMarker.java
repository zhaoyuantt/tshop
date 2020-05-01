package com.taotao.item.freemarker;

import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * Freemarker测试类
 * @author zhaoyuan
 * @date 2019年5月1日 下午8:11:05
 */
public class TestFreeMarker {

	/**
	 * 根据模板生成html页面
	 * freemarker使用方法
	 */
	@Test
	public void testFreeMarker1(){
		Writer out = null;
		try {
			//1.创建一个模板文件 my.flt
			//2.创建一个Configuration对象
			Configuration configuration = new Configuration(Configuration.getVersion());
			//3.设置模板所在路径 
			configuration.setDirectoryForTemplateLoading(new 
					File("E:/workSpace-me/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
			//4.设置模板字符集，一般是UTF-8
			configuration.setDefaultEncoding("utf-8");
			//5.使用Configuration对象加载一个模板文件，指定模板文件的文件名
			Template template = configuration.getTemplate("my.ftl");
			//6.创建一个数据集，可以是pojo，map
			Map map = new HashMap<>();
			map.put("hello", "suofeiya,you are my image");
			//7.创建一个Writer流，指定输出文件的路径和文件名
			out = new FileWriter("E:/temp/outhtml/my.html");
			//8.使用模板对象的process方法输出文件
			template.process(map, out);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				//8.关闭流
				if(null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 输出pojo属性
	 */
	@Test
	public void testFreeMarker2(){
		Writer out = null;
		try {
			Configuration configuration = new Configuration(Configuration.getVersion());
			//设置模板所在路径 
			configuration.setDirectoryForTemplateLoading(new 
					File("E:/workSpace-me/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
			configuration.setDefaultEncoding("utf-8");
			//使用Configuration对象加载一个模板文件，指定模板文件的文件名
			Template template = configuration.getTemplate("goddess.ftl");
			//数据集
			Goddess goddess = new Goddess(1, "索菲亚", "南京");
			Map<Object, Goddess> map = new HashMap<>();
			map.put("goddess", goddess);
			//创建一个Writer流，指定输出文件的路径和文件名
			out = new FileWriter("E:/temp/outhtml/goddess.html");
			//使用模板对象的process方法输出文件
			template.process(map, out);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 遍历集合,if else,日期类型处理，null值处理
	 */
	@Test
	public void testFreeMarker3(){
		Writer out = null;
		try {
			Configuration configuration = new Configuration(Configuration.getVersion());
			//设置模板所在路径 
			configuration.setDirectoryForTemplateLoading(new 
					File("E:/workSpace-me/taotao-item-web/src/main/webapp/WEB-INF/ftl"));
			configuration.setDefaultEncoding("utf-8");
			//使用Configuration对象加载一个模板文件，指定模板文件的文件名
			Template template = configuration.getTemplate("goddessList.ftl");
			//数据集
			ArrayList<Goddess> goddessList = new ArrayList<>();
			Goddess goddessS = new Goddess(1, "索菲亚", "南京",new Date());
			Goddess goddessD = new Goddess(1, "邓紫棋", "香港",new Date(),"歌手");
			goddessList.add(goddessS);
			goddessList.add(goddessD);
			Map<Object, ArrayList<Goddess>> map = new HashMap<>();
			map.put("goddessList", goddessList);
			//创建一个Writer流，指定输出文件的路径和文件名
			out = new FileWriter("E:/temp/outhtml/goddessList.html");
			//使用模板对象的process方法输出文件
			template.process(map, out);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
