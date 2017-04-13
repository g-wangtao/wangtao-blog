package com.wangtao.blog.email.exception;

import com.wangtao.blog.common.exception.BusinessException;

/**
 * @ClassName:EmailException
 * @author: KarlWang
 * @Description: TODO(邮件发送异常管理类) 
 * @date:2017年4月11日 下午8:12:37
 * @see com.wangtao.blog.email.exception.EmailException
 */
public class EmailException extends BusinessException {


	/** 
	 * @Fields serialVersionUID : TODO(序列化ID) 
	 */ 
	private static final long serialVersionUID = 1L;

	public EmailException() {
		super();
	}

	public EmailException(String msg) {
		super(msg);
	}
	
	public EmailException(Exception e) {
		super(e.getMessage());
	}

	public EmailException(String code, String msg) {
		super(code, msg);
	}

	public EmailException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
	
}