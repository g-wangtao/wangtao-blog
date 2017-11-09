package com.wangtao.blog.model.entity.blogger;

import com.wangtao.blog.model.base.BaseEntity;

/**
 * @ClassName:BloggerPasswordEntity
 * @author: KarlWang
 * @Description: TODO(博主密码实体对象) 
 * @date:2017年10月10日 下午8:20:13
 * @see com.wangtao.blog.model.entity.blogger.BloggerPasswordEntity
 */
public class BloggerPasswordEntity extends BaseEntity
{

	/** 
	 * @Fields serialVersionUID : TODO(序列号ID) 
	 */ 
	private static final long serialVersionUID = 1L;

	
	private String name; // 名称
	
	private String phone; // 绑定手机
	
	private String password; // 密码
	
	private String checkPassword; // 验证密码
	
	private String payPassword; // 支付密码

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
}
