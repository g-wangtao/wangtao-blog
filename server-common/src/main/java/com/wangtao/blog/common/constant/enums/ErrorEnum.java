package com.wangtao.blog.common.constant.enums;

public enum ErrorEnum 
{
	// 空请求参数异常
	PARAMETER_EMPTY_ERROR(1001,"请求参数不能为空！","空请求参数异常"),
	
	;
	
	private int errorCode; // 异常编码
	
	private String errorMsg; // 异常信息
	
	private String desc; // 描述

	private ErrorEnum(int errorCode,String errorMsg,String desc) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.desc = desc;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getDesc() {
		return desc;
	}	
}
