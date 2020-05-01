package com.taotao.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

/**
 * ActiveMQ 测试类
 * @author zhaoyuan
 * @date 2019年4月29日 下午6:43:39
 */
public class NoTestActiveMQ {

	//*********************************************
	//               queue 方式  点到点
	//*********************************************
	
	/**
	 * 产生消息 发送端
	 * Producer 生产者
	 */
	@Test
	public void notestQueueProducer(){
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			//1.创建连接工厂对象ConnectionFacrory对象，需要指定mq服务的ip和端口
			ConnectionFactory connectionFactory = 
					new ActiveMQConnectionFactory("tcp://192.168.25.132:61616");
			//2.使用ConnectionFacrory对象创建一个Connection对象
			connection = connectionFactory.createConnection();
			//3.调用Connection的start方法，开启连接
			connection.start();
			//4.使用Connection对象创建一个Session对象,arg0:是否开启事务,一般不使用事务。 
			//arg1:应答模式：自动应答与手动应答，一般是自动应答。
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.使用Session对象创建一个Destination对象，有两种形式，queue，topic。现在使用queue
			Queue queue = session.createQueue("testQ");   //消息队列的名称
			//6.使用Session对象创建一个MessageProducer对象
			producer = session.createProducer(queue);
			//7.创建一个TestMessage对象
			TextMessage textMessage = new ActiveMQTextMessage();
			textMessage.setText("Hello ActiveMQ,Queue!!!");
			//8.发送消息
			producer.send(textMessage);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//9.关闭资源
			try {
				producer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 消费者，接收消息
	 * Consumer
	 */
	@Test
	public void notestQueueConsumer(){
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			//1.创建一个ConnectionFactory对象，指定mq服务器的Ip和端口
			ConnectionFactory connectionFactory = new 
					ActiveMQConnectionFactory("tcp://192.168.25.132:61616");
			//2.从ConnectionFactory对象中获得一个Connection对象
			connection = connectionFactory.createConnection();
			//3.调用Connection方法的start方法开启连接
			connection.start();
			//4.使用Connection对象创建一个Session对象
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.使用Session对象创建一个Distination,和发送端保持一致topic，并且话题的名称一致
			Queue queue = session.createQueue("testQ");
			//6.使用Session对象创建一个Consucer对象
			consumer = session.createConsumer(queue);
			//7.接收消息
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
					String text = "";
					try {
						text = textMessage.getText();
						System.out.println("接收到的ActiveMQ的queue消息："+text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			//等待键盘输入
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//关闭资源
			try {
				consumer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//*********************************************
	//               topic 方式  广播
	//*********************************************
	
	/**
	 * 消息产生者
	 * Producer
	 */
	@Test
	public void notestProducerTopic(){
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			//1.创建ConnectionFactory对象，指定mq服务器的Ip和端口
			ConnectionFactory connectionFactory = 
					new ActiveMQConnectionFactory("tcp://192.168.25.132:61616");
			//2.使用ConnectionFactory对象创建Connection对象
			connection = connectionFactory.createConnection();
			//3.开启连接
			connection.start();
			//4.使用Connection对象创建一个Session对象
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.使用Session对象创建一个Distinction对象
			Topic topic = session.createTopic("testTopic");
			//6.使用Session对象创建一个Producer对象
			producer = session.createProducer(topic);
			//7.使用Producer对象发送消息
			TextMessage message = new ActiveMQTextMessage();
			message.setText("Hello,ActiveMQ topic!!!!");
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != producer){
					producer.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				if(null != session){
					session.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				if(null != connection){
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 消费者 接收消息
	 * Consumer
	 */
	@Test
	public void notestConsumerTopic(){
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		try {
			//1.创建ConnectionFactory对象，指定mq服务器的Ip和端口
			ConnectionFactory connectionFactory = 
					new ActiveMQConnectionFactory("tcp://192.168.25.132:61616");
			//2.使用ConnectionFactory对象创建连接
			connection = connectionFactory.createConnection();
			//3.开启连接
			connection.start();
			//4.使用Connection对象创建一个Session对象
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//5.使用Session对象创建一个Distication对象，主题与发送者保持一致
			Topic topic = session.createTopic("testTopic");
			//6.使用Session对象创建一个MessageConsumer对象
			consumer = session.createConsumer(topic);
			//7.接收消息
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;
					String text = "";
					try {
						text = textMessage.getText();
						System.out.println("接收到ActiveMQ广播的消息："+text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			//等待键盘输入
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(null != consumer){
					consumer.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				if(null != session){
					session.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			try {
				if(null != connection){
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
