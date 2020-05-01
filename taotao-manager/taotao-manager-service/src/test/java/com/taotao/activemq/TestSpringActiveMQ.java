package com.taotao.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Spring整合ActiveMQ测试类
 * @author zhaoyuan
 * @date 2019年4月30日 上午11:18:01
 */
public class TestSpringActiveMQ {

	/**
	 * 发送点到点的消息
	 * Producer
	 */
	@Test
	public void testjmsTemplate(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		//从容器中获得Destination对象
		Destination destination = (Destination) applicationContext.getBean("testQ1");
		//发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("suofeiya,you are my image!");
				return textMessage;
			}
		});
	}
}
