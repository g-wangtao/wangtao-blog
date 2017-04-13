package com.wangtao.blog.email.util;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @ClassName:SendMailUtil
 * @author: KarlWang
 * @Description: TODO(邮件发送工具类) 
 * @date:2017年4月12日 下午4:21:00
 * @see com.wangtao.blog.email.util.SendMailUtil
 */
public class SendMailUtil {
	
	private static final Logger LOGGER = Logger.getLogger(SendMailUtil.class);

	/**
	 * 功能描述：邮件发送
	 * 模块作者：ZOUYONG
	 * 开发时间：2016年9月7日 下午5:10:13
	 * 更新记录：
	 * 返回数据：void
	 */
	public static void sendMessage(
			JavaMailSenderImpl mailSender, String email, String title, String content) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo(email);						// 接受者
			mail.setFrom(mailSender.getUsername());	// 发送者
			mail.setSubject(title);					// 主题
			mail.setText(content);					// 邮件内容
			mailSender.send(mail);
		} catch (Exception e) {
			LOGGER.error("邮件发送异常：" + e.getMessage(), e);
		}
		LOGGER.info("邮件发送完成：接收者["+email+"]");
	}
	
}
