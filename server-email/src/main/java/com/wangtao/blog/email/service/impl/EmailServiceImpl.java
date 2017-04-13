package com.wangtao.blog.email.service.impl;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.wangtao.blog.common.threadexecutor.ThreadTaskExecutor;
import com.wangtao.blog.common.util.StringUtils;
import com.wangtao.blog.common.util.des.DesPwdUtil;
import com.wangtao.blog.email.entity.EmailEntity;
import com.wangtao.blog.email.exception.EmailException;
import com.wangtao.blog.email.service.IEmailService;
import com.wangtao.blog.email.thread.EmailSendRunnable;
import com.wangtao.blog.email.util.SendMailUtil;

/**
 * @ClassName:EmailServiceImpl
 * @author: KarlWang
 * @Description: TODO(邮件发送实现类) 
 * @date:2017年4月11日 下午8:14:15
 * @see com.wangtao.blog.email.service.impl.EmailServiceImpl
 */
public class EmailServiceImpl implements IEmailService {
	
	private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);
	
	// 线程管理类
	private ThreadTaskExecutor threadTaskExecutor;
	
	// Spring邮件发送对象
	private JavaMailSenderImpl mailSender;
	
	private static String desKey = "DIDONG-EMAIL-SENDER";
    
    /**
     * 功能描述：
     * 模块作者：zouyong
     * 开发时间：2015年12月8日 上午11:34:33
     * 更新记录：
     * 返回数据：String
     */
    public String syncSendEmailByMessage(EmailEntity email) throws Exception {
    	if(null == email) {
        	throw new EmailException("邮件数据对象不能为空！");
    	}
    	String content = email.getContent();
    	if(StringUtils.isBlank(content)) {
        	throw new EmailException("邮件内容不能为空！");
    	}
    	String emails = email.getEmails();
    	if(StringUtils.isBlank(emails)) {
        	throw new EmailException("邮件接收人不能为空！");
    	}
    	logger.info("~~~开始发送邮件：接收者["+emails+"];内容["+content+"]");
    	
    	// 判断发送方邮件账户密码是否加密
    	if(email.isPwd()) {
    		String userName = mailSender.getUsername();
    		if(StringUtils.isNotBlank(userName)) {
    			mailSender.setUsername(DesPwdUtil.decrypt(userName, desKey));
    		}
    		String password = mailSender.getPassword();
    		if(StringUtils.isNotBlank(password)) {
    			mailSender.setPassword(DesPwdUtil.decrypt(password, desKey));
    		}
    	}
    	
    	String[] ary = emails.split(";");
    	for (String em : ary) {
    		if(StringUtils.isBlank(em)) {
				continue;
			}
			SendMailUtil.sendMessage(mailSender, em, email.getTitle(), content);
		}
    	return null;
    }

	
    public void asyncSendEmailByMessage(EmailEntity email) throws Exception {
    	if(null == email) {
        	throw new EmailException("邮件数据对象不能为空！");
    	}
    	EmailSendRunnable emailRun = new EmailSendRunnable();
    	emailRun.setEmailInfo(email);
    	emailRun.setEmailInfoService(this);
    	threadTaskExecutor.getThreadPool().execute(emailRun);
    }

	/**
	 * 作者：zouyong
	 * 时间：2015年12月8日 下午12:32:01
	 * @param threadTaskExecutor the threadTaskExecutor to set
	 */
	public void setThreadTaskExecutor(ThreadTaskExecutor threadTaskExecutor) {
		this.threadTaskExecutor = threadTaskExecutor;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年9月7日 下午5:06:10
	 * @return the mailSender
	 */
	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年9月7日 下午5:06:10
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

}

