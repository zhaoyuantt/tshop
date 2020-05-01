package com.taotao.activemq;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 整合 ActiveMQ 测试类
 * @author zhaoyuan
 * @date 2019年4月30日 下午2:04:45
 */
public class TestSpringActiveMQ {

	/**
	 * 消费者
	 * Consumer 接收消息
	 */
	@Test
	public void testQueueConsumer(){
		//初始化Spring容器
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
