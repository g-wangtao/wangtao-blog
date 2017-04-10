package com.wangtao.blog.model.entity;

import java.util.Date;

public class TestEntity {

	private long id;

	private String testName;

	private String createUser;

	private Date createDate;

	private String modifyUser;

	private Date modifyDate;
	
	private char active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@Override
	public String toString() {
		return "BTest [id=" + id + ", testName=" + testName + ", createUser=" + createUser + ", createDate="
				+ createDate + ", modifyUser=" + modifyUser + ", modifyDate=" + modifyDate + "]";
	}

}
