package com.wangtao.blog.model.entity.blogger;

import java.util.Date;

import com.wangtao.blog.model.base.entity.BaseEntity;

/**
 * @ClassName:Blogger
 * @author: KarlWang
 * @Description: TODO(博主实体)
 * @date:2017年5月3日 下午4:20:33
 * @see com.wangtao.blog.model.entity.blogger.BloggerEntity
 */
public class BloggerEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(序列号ID)
	 */
	private static final long serialVersionUID = 1L;

	private String userName; // 用户名

	private String password; // 密码

	private String type; // 类型

	private String nickName; // 昵称

	private String email; // 博主邮箱

	private String sign; // 个性签名

	private String proFile; // 个人简介

	private String headImgUrl; // 头像地址

	private Date lastLogin; // 最后登陆时间

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getProFile() {
		return proFile;
	}

	public void setProFile(String proFile) {
		this.proFile = proFile;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
