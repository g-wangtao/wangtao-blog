package com.wangtao.blog.model.entity.friendlylink;

import com.wangtao.blog.model.base.BaseEntity;

/**
 * @ClassName:FriendlyLinkEntity
 * @author: KarlWang
 * @Description: TODO(友情链接实体对象) 
 * @date:2017年10月17日 下午2:32:12
 * @see com.wangtao.blog.model.entity.friendlylink.FriendlyLinkEntity
 */
public class FriendlyLinkEntity extends BaseEntity {
	
	/** 
	 * @Fields serialVersionUID : TODO(序列号ID) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private String url;
	
	private String remark;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
