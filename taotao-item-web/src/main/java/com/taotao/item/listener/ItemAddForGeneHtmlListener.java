package com.taotao.item.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.item.pojo.ItemCustom;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 监听商品添加事件，生成html页面
 * @author zhaoyuan
 * @date 2019年5月2日 下午2:10:39
 */
public class ItemAddForGeneHtmlListener implements MessageListener{

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private ItemService itemService;
	
	private ItemParamService itemParamService;
	
	@Value("${ITEM_OUT_HTML_PATH}")
	private String ITEM_OUT_HTML_PATH;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = null;
		Long iid = null;
		Writer out = null;
		if(message instanceof TextMessage){
			try {
				textMessage = (TextMessage) message;
				String itemId = textMessage.getText();
				iid = Long.parseLong(itemId);
				System.out.println("************************************");
				System.out.println("接收到ActiveMQ发送的商品Id:"+iid);
				System.out.println("************************************");
				
				//开始生产html页面
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/*if(null == freeMarkerConfigurer){
					freeMarkerConfigurer = new FreeMarkerConfigurer();
					freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/ftl/");
					freeMarkerConfigurer.setDefaultEncoding("utf-8");
				}*/
				
				//加载模板文件
				//不知道为什么，spring注入的FreeMarkerConfigurer的对象有时为null值
				if(null != freeMarkerConfigurer){
					Configuration configuration = freeMarkerConfigurer.getConfiguration();
					Template template = configuration.getTemplate("item.ftl");
					//数据集
					TbItem item = itemService.getItemById(iid);
					//强转
					ItemCustom itemCustom = new ItemCustom(item);
					TbItemDesc itemDesc = itemService.getItemDescByItemId(iid);
					String paramDate = itemParamService.getItemParamItem(iid);
					HashMap<Object, Object> map = new HashMap<>();
					map.put("item", itemCustom);
					map.put("itemDesc", itemDesc);
					map.put("itemParam", paramDate);
					
					//输出
					//out = new FileWriter(new File(ITEM_OUT_HTML_PATH+iid+".html"));
					//解决乱码问题
					out = new OutputStreamWriter(new FileOutputStream(new File(ITEM_OUT_HTML_PATH+iid+".html")),"utf-8");
					System.out.println("************************************");
					System.out.println("生成商品静态页面："+ITEM_OUT_HTML_PATH+iid+".html");
					System.out.println("************************************");
					template.process(map, out);
				}
				
			} catch (JMSException e) {
				e.printStackTrace();
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
				if(null != out){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}
