package com.wangtao.blog.common.exception;

import com.wangtao.blog.common.exception.BusinessException;

/**
 * @ClassName:HttpUtilException
 * @author: KarlWang
 * @Description: TODO(HTTP工具异常类) 
 * @date:2017年9月12日 下午7:50:58
 * @see com.wangtao.blog.common.exception.HttpUtilException
 */
public class HttpUtilException extends BusinessException {

	/**
	 * @Fields serialVersionUID : TODO(序列号ID)
	 */
	private static final long serialVersionUID = 1L;

	public HttpUtilException() {
		super();
	}

	public HttpUtilException(String msg) {
		super(msg);
	}

	public HttpUtilException(Exception e) {
		super(e.getMessage());
	}

	public HttpUtilException(String code, String msg) {
		super(code, msg);
	}

	public HttpUtilException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
