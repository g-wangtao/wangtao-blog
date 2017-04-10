package com.wangtao.blog.common.exception;

import java.io.Serializable;

/**
 * Created by Barlow on 2017/3/21.
 */
public class BusinessException extends RuntimeException implements Serializable,IException {
    
	/**
     * 序列化ID
     */
	private static final long serialVersionUID = 2812034057354034515L;

	/**
     * 异常code
     */
    protected String errCode;

    /**
     * 异常信息
     */
    private String nativeMsg;

    /**
     * 异常 arguments
     */
    private Object[] arguments;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.errCode = code;
    }

    public BusinessException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.errCode = code;
    }

    public BusinessException(String code, String msg, String natvieMsg) {
        super(msg);
        this.errCode = code;
        this.nativeMsg = natvieMsg;
    }

    public BusinessException(String code, String msg,String natvieMsg, Throwable cause) {
        super(msg, cause);
        this.errCode = code;
        this.nativeMsg = natvieMsg;
    }

    public BusinessException(String code,Object... args) {
        super();
        this.errCode = code;
        this.arguments = args;
    }

    public BusinessException(String code,String msg, Object... args) {
        super(msg);
        this.errCode = code;
        this.arguments = args;
    }

	public String getErrorCode() {
		// TODO Auto-generated method stub
		return this.errCode;
	}

	public String getNativeMessage() {
		// TODO Auto-generated method stub
		return this.nativeMsg;
	}

	public void setErrorArguments(Object... objects) {
		// TODO Auto-generated method stub
		this.arguments = objects;
	}

	public Object[] getErrorArguments() {
		// TODO Auto-generated method stub
		return this.arguments;
	}

   
}
