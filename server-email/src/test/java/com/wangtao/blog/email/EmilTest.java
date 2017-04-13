package com.wangtao.blog.email;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.email.entity.EmailEntity;
import com.wangtao.blog.email.service.IEmailService;

/**
 * @ClassName:EmilTest
 * @author: KarlWang
 * @Description: TODO(邮件功能测试类) 
 * @date:2017年4月13日 下午4:26:58
 * @see com.wangtao.blog.email.EmilTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:mail/mail.xml"})
public class EmilTest{
	
	@Resource
	private IEmailService emailService;
	
	//@Test
	public void testAsyncSendEmailByMessage() throws Exception {
		EmailEntity email = new EmailEntity();
		email.setContent("hello spring mail ！");
		email.setEmails("773995514@qq.com");
		//email.setPwd(true);
		email.setTitle("sync");
		emailService.syncSendEmailByMessage(email);
	}
    
	@Test
	public void testSyncSendEmailByMessage() throws Exception {
		EmailEntity email = new EmailEntity();
		email.setContent("hello spring mail ！");
		email.setEmails("773995514@qq.com");
		//email.setPwd(true);
		email.setTitle("aync");
		emailService.syncSendEmailByMessage(email);
	}
	
}
