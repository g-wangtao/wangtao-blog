package com.wangtao.blog.common.exception;

public interface IException {
	
	String getErrorCode();

    String getNativeMessage();

    void setErrorArguments(Object... objects);

    Object[] getErrorArguments();
	
}
