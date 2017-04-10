package com.wangtao.blog.common.util.base64;

import java.io.IOException;

/**
 * @ClassName:CEFormatException
 * @author: KarlWang
 * @Description: TODO(解密出错异常) 
 * @date:2017年4月10日 下午2:56:49
 * @see com.wangtao.blog.common.util.base64.CEFormatException
 */
public class CEFormatException extends IOException {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -5645294385484242272L;

	public CEFormatException(String s) {
        super(s);
	}
	
}
