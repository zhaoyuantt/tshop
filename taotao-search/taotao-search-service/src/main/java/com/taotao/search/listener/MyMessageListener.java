package com.taotao.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收ActiveMQ发送的消息
 * @author zhaoyuan
 * @date 2019年4月30日 下午1:48:56
 */
public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		String text = "";
		try {
			text = textMessage.getText();
			System.out.println("接收到ActiveMQ发送的消息："+text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
