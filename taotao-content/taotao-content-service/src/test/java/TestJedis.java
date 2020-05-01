import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Jedis客户端测试
 * @author zhaoyuan
 * @date 2019年3月3日 下午9:37:20
 */
public class TestJedis {

	@Test
	public void testJedis() throws Exception {
		//创建一个jedis对象，需要指定服务的ip和端口号
		Jedis jedis = new Jedis("192.168.25.132", 6379);
		//直接操作数据库
		jedis.set("jedis-key", "1234");
		String result = jedis.get("jedis-key");
		System.out.println(result);
		//关闭jedis
		jedis.close();
	}
	
	/*@Test
	public void testJedisCluster(){
		JedisCluster jedisCluster = null;
		try {
			java.util.Set<HostAndPort> nodes = new HashSet<>();
			nodes.add(new HostAndPort("192.168.25.132", 7001));
			nodes.add(new HostAndPort("192.168.25.132", 7002));
			nodes.add(new HostAndPort("192.168.25.132", 7003));
			nodes.add(new HostAndPort("192.168.25.132", 7004));
			nodes.add(new HostAndPort("192.168.25.132", 7005));
			nodes.add(new HostAndPort("192.168.25.132", 7006));
			jedisCluster = new JedisCluster(nodes);
			jedisCluster.set("suofeiya", "You are my image");
			String result = jedisCluster.get("suofeiya");
			System.out.println(result);
		} catch (Exception e) {
			
		} finally {
			jedisCluster.close();
		}
	}*/
	
}
