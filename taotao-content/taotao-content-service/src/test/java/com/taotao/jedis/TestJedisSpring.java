package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring整合jedis客户端测试
 * @author zhaoyuan
 * @date 2019年3月3日 下午10:27:19
 */
public class TestJedisSpring {

	/**
	 * 单机版测试
	 */
	@Test
	public void TestJedisClientPool(){
		try {
			//初始化Spring容器，获得JedisPool对象
			ApplicationContext applicationConfig = new
					ClassPathXmlApplicationContext("classpath:spring\\applicationContext-redis.xml");
			JedisClient  jedisClient = applicationConfig.getBean(JedisClient.class);
			String result = jedisClient.get("suofeiya");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
