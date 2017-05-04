package com.wangtao.blog.model.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author
 *
 */
public class BaseEntity implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 5226101072486456137L;

	private long id; // 编号
	
	private char active; // 是否有效

	private String crateUser; // 创建人

	private Date crateDate; // 创建时间

	private String modifyUser; // 修改人

	private Date modifyDate; // 修改时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCrateUser() {
		return crateUser;
	}

	public void setCrateUser(String crateUser) {
		this.crateUser = crateUser;
	}

	public Date getCrateDate() {
		return crateDate;
	}

	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

}
