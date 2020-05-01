package com.taotao.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.search.service.IndexItemService;

/**
 * 监听商品添加事件，同步索引库 
 * @author zhaoyuan
 * @date 2019年4月30日 下午3:05:23
 */
public class ItemChangeListener implements MessageListener{
	
	@Autowired
	private IndexItemService indexItemService;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = null;
		Long itemId = null;
		if(message instanceof TextMessage){
			try {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
				System.out.println("************************************");
				System.out.println("接收到ActiveMQ发送的商品Id:"+itemId);
				System.out.println("************************************");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		//向索引库添加文档
		indexItemService.addDocument(itemId);
		
	}
	
	
}
