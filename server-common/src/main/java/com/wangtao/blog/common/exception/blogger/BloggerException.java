package com.wangtao.blog.common.exception.blogger;

import com.wangtao.blog.common.exception.BusinessException;

/**
 * @ClassName:BloggerException
 * @author: KarlWang
 * @Description: TODO(博主异常类)
 * @date:2017年5月3日 下午7:00:37
 * @see com.wangtao.blog.common.exception.blogger.BloggerException
 */
public class BloggerException extends BusinessException {

	/**
	 * @Fields serialVersionUID : TODO(序列号ID)
	 */
	private static final long serialVersionUID = 1L;

	public BloggerException() {
		super();
	}

	public BloggerException(String msg) {
		super(msg);
	}

	public BloggerException(Exception e) {
		super(e.getMessage());
	}

	public BloggerException(String code, String msg) {
		super(code, msg);
	}

	public BloggerException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
