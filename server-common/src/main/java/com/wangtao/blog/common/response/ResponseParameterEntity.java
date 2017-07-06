package com.wangtao.blog.common.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName:ResponseParameterEntity
 * @author: KarlWang
 * @Description: TODO(系统请求响应对象)
 * @date:2017年6月5日 下午2:51:22
 * @see com.wangtao.blog.common.response.ResponseParameterEntity
 */
@XmlRootElement(name = "ResponseParameterEntity")
public class ResponseParameterEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(序列化ID)
	 */
	private static final long serialVersionUID = 8305083615443730276L;

	// 公共实体（Java各数据类型皆可）
	private Object responseEntity;

	// 是否成功标志
	private boolean resultFlag;

	// 失败/其他-原因
	private String message;

	// 处理数量
	private Long count;

	// 分页最大记录数
	private int limit;

	// 分页开始记录数
	private int start;

	public Object getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(Object responseEntity) {
		this.responseEntity = responseEntity;
	}

	public boolean isResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCount() {
		return null == count ? 0l : count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}