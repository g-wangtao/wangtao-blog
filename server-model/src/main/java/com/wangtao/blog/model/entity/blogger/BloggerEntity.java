package com.wangtao.blog.model.entity.blogger;

import com.wangtao.blog.model.base.entity.BaseEntity;

/**
 * @ClassName:Blogger
 * @author: KarlWang
 * @Description: TODO(博主实体) 
 * @date:2017年5月3日 下午4:20:33
 * @see com.wangtao.blog.model.entity.blogger.BloggerEntity
 */
public class BloggerEntity extends BaseEntity{

	/** 
	 * @Fields serialVersionUID : TODO(序列号ID) 
	 */ 
	private static final long serialVersionUID = 1L;

	private String account; // 账号
	
	private String password; // 密码
	
	private String nickName; // 昵称
	
	private String sign; // 个性签名
	
	private String proFile; // 个人简介
	
	private String imageName; // 博主头像

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
