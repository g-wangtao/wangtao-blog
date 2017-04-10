package com.wangtao.blog.model;

import java.io.Serializable;

/**
 * 
 * @author Barlow
 *
 */
public class BlogTypeEntity implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2916325822467200375L;
	
	// 编号
	private Integer id; 
	// 博客类型名称
	private String typeName; 
	// 数量
	private Integer blogCount; 
	// 排序  从小到大排序显示
	private Integer orderNo; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public String toString() {
		return "BlogType [id=" + id + ", typeName=" + typeName + ", blogCount=" + blogCount + ", orderNo=" + orderNo
				+ "]";
	}
	
}
