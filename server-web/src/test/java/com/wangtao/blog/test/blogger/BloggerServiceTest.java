package com.wangtao.blog.test.blogger;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.common.util.md5.CryptoUtil;
import com.wangtao.blog.core.blogger.IBloggerService;
import com.wangtao.blog.model.entity.blogger.BloggerEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BloggerServiceTest {
	
	@Autowired
	IBloggerService bloggerService;
	
	//@Test
	public void testSave() {
		BloggerEntity blogger = new BloggerEntity();
		blogger.setUserCode("admin");
		blogger.setPassword(CryptoUtil.digestByEncode("123456"));
		blogger.setType("admin");
		blogger.setNickName("大无畏");
		blogger.setEmail("773995514@qq.com");
		blogger.setProFile("Hello world");
		blogger.setActive('Y');
		blogger.setCreateDate(new Date());
		blogger.setCreateUser("admin");

		try{
			System.out.println(bloggerService.saveByEntity(blogger));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery() {
		BloggerEntity blogger = bloggerService.queryByUserCode("admin");
		boolean success =  CryptoUtil.digestByEncode("123456").equals(blogger.getPassword());
		if(success) {
			System.out.println("success");
		} else{
			System.out.println("error");
		}
		
	}
}
