package com.wangtao.blog.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:BaseEntity
 * @author: KarlWang
 * @Description: TODO(系统基础实体类) 
 * @date:2017年5月4日 下午2:13:45
 * @see com.wangtao.blog.model.base.BaseEntity
 */
public class BaseEntity implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 5226101072486456137L;

	private long id; // 编号
	
	private char active = 'Y'; // 是否有效

	private String createUser; // 创建人

	private Date createDate; // 创建时间

	private String modifyUser; // 修改人

	private Date modifyDate; // 修改时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
