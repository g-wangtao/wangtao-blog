package com.wangtao.blog.email.entity;

import com.wangtao.blog.model.base.BaseEntity;

/**
 * @ClassName:EmailEntity
 * @author: KarlWang
 * @Description: TODO(邮件发送实体对象)
 * @date:2017年4月11日 下午6:47:41
 * @see com.wangtao.blog.email.entity.EmailEntity
 */
public class EmailEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(序列化ID)
	 */
	private static final long serialVersionUID = 1L;

	// 邮件内容
	private String content;

	// 发送到的手机号列表(多个邮箱用;号隔开)
	private String emails;

	// 邮件标题
	private String title;

	// 邮件发送状态:0-失败、1-成功
	private Integer status;

	// 邮件描述
	private String smsDesc;

	// 邮件发送返回值
	private String sendResult;

	// 是否保存消息到数据库
	private boolean isSaveMsg = false;

	// 发送方的邮件账号、密码，是否加密（默认不加密）
	private boolean isPwd = false;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSmsDesc() {
		return smsDesc;
	}

	public void setSmsDesc(String smsDesc) {
		this.smsDesc = smsDesc;
	}

	public String getSendResult() {
		return sendResult;
	}

	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	public boolean isSaveMsg() {
		return isSaveMsg;
	}

	public void setSaveMsg(boolean isSaveMsg) {
		this.isSaveMsg = isSaveMsg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPwd() {
		return isPwd;
	}

	public void setPwd(boolean isPwd) {
		this.isPwd = isPwd;
	}

}
