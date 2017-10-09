package com.wangtao.blog.model;

import com.wangtao.blog.model.base.BaseEntity;

/**
 * @ClassName:SequenceEntity
 * @author: KarlWang
 * @Description: TODO(序列实体类) 
 * @date:2017年7月21日 下午3:09:40
 * @see com.wangtao.blog.model.SequenceEntity
 */
public class SequenceEntity extends BaseEntity {

	/** 
	 * @Fields serialVersionUID : TODO(序列化ID) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String seqName ; // 序列名
	
	private String currentVal; // 序列值
	
	private String incrementVal; // 步增值

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getCurrentVal() {
		return currentVal;
	}

	public void setCurrentVal(String currentVal) {
		this.currentVal = currentVal;
	}

	public String getIncrementVal() {
		return incrementVal;
	}

	public void setIncrementVal(String incrementVal) {
		this.incrementVal = incrementVal;
	}
}
