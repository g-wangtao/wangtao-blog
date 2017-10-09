package com.wangtao.blog.test.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName:JedisTest
 * @author: KarlWang
 * @Description: TODO(Jedis测试类) 
 * @date:2017年8月28日 下午5:19:58
 * @see com.wangtao.blog.test.jedis.JedisTest
 */
public class JedisTest {

	/**
	 * @Title: TestJedisConnect 
	 * @Description: TODO(Simple test) 
	 * @param   
	 * @return void 返回类型 
	 * @throws
	 */
	@Test
	public void TestJedisConnect(){
		Jedis jedis = new Jedis("192.168.1.217",6379);
		jedis.set("Hello", "World"); //  
		System.out.println(jedis.get("Hello"));
		jedis.close(); // 关闭Redis连接
	}
	
	/**
	 * @Title: TestJedisConnectd 
	 * @Description: TODO(
	 * Jedis对象并不是线程安全的，在多线程下使用同一个Jedis对象会出现并发问题。
	 * 为了避免每次使用Jedis对象时都需要重新构建，Jedis提供了JedisPool。
	 * JedisPool是基于Commons Pool 2实现的一个线程安全的连接池。
	 * ) 
	 * @param  设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	
	@Test
	public void TestJedisConnectd() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		JedisPool pool = new JedisPool(jedisPoolConfig,"192.168.1.217",6379);
		
		Jedis jedis = null;
		try{
			jedis = pool.getResource();  // 在Jedis的构造函数中，会创建真正与Redis Server通信的Client对象：
			jedis.set("hey", "World"); // 初始化Jedis对象并不会与Redis Server建立连接，连接发生在第一次执行命令时。
			System.out.println(jedis.get("hey"));
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.close(); // 将Jedis对象归还到连接池中
				jedis = null;
			}
		}
		
		pool.close();
	}
	
}
