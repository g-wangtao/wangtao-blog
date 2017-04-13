package com.wangtao.blog.email.thread;

import org.apache.log4j.Logger;

import com.wangtao.blog.email.entity.EmailEntity;
import com.wangtao.blog.email.exception.EmailException;
import com.wangtao.blog.email.service.IEmailService;

/**
 * @ClassName:EmailSendRunnable
 * @author: KarlWang
 * @Description: TODO(异步发送邮件对象) 
 * @date:2017年4月12日 下午4:24:29
 * @see com.wangtao.blog.email.thread.EmailSendRunnable
 */
public class EmailSendRunnable implements Runnable {
	
	private final Logger logger = Logger.getLogger(EmailSendRunnable.class);
	
	private EmailEntity emailInfo;
	
	private IEmailService emailInfoService;
	
	@Override
	public void run() {
		if(null == emailInfo) {
			throw new EmailException("异步执行邮件发送，发送失败：邮件数据对象为空！");
		}
		if(null == emailInfoService) {
			throw new EmailException("获取执行邮件发送Service类失败！");
		}
		try {
			logger.info("开始执行-异步邮件发送功能！");
			// 执行邮件发送
			emailInfoService.syncSendEmailByMessage(emailInfo);
			logger.info("结束执行-异步邮件发送功能！");
		} catch (Exception e) {
			logger.error("异步邮件发送功能执行失败："+e.getMessage(),e);
		}
	}

	public void setEmailInfo(EmailEntity emailInfo) {
		this.emailInfo = emailInfo;
	}

	public void setEmailInfoService(IEmailService emailInfoService) {
		this.emailInfoService = emailInfoService;
	}

}
