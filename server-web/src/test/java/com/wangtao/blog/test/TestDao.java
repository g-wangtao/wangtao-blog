package com.wangtao.blog.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.dao.ITestDao;
import com.wangtao.blog.model.entity.TestEntity;


/**
 * Created by codingBoy on 16/11/27.
 * 配置spring和junit整合，这样junit在启动时就会加载spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestDao {
	
	@Resource
	private ITestDao testDao;
	
	@Test
	public void testInsert(){
		TestEntity testEntity = new TestEntity();
		testEntity.setTestName("junit init test");
		testEntity.setCreateUser("admin");
		testEntity.setCreateDate(new Date());
		int i = testDao.insert(testEntity);
		
		System.out.println(i);
	}
	
}
