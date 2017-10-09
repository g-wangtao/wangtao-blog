package com.wangtao.blog.model.entity.region;

import java.util.List;

import com.wangtao.blog.model.base.BaseEntity;

/**
 * @ClassName:BaseRegionEntity
 * @author: KarlWang
 * @Description: TODO(CN行政区域实体) 
 * @date:2017年5月29日 下午2:29:38
 * @see com.wangtao.blog.model.entity.region.BaseRegionEntity
 */
public class BaseRegionEntity extends BaseEntity {

	/** 
	 * @Fields serialVersionUID : TODO(序列号ID) 
	 */ 
	private static final long serialVersionUID = -5630243009356989216L;
	
	private String regionCode; // 行政编码
	
	private String regionName; // 行政名称
	
	private String regionType; // 区域类型：country-0 、province-1、city-2、county-3、town-4、street-5
	
	private String briefName; // 简称
	
	private String availableName; // 可用名称
	
	private String parentCode; // 上级行政编码
	
	private String degree; // 级别：country-国家 、province-省、city-市、county-县、town-乡镇、street-街道
	
	private String pinyin; // 区域拼音
	
	private String descripion; // 区域描述
	
	private List<BaseRegionEntity> regionChindren; // 行政区域树

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public String getAvailableName() {
		return availableName;
	}

	public void setAvailableName(String availableName) {
		this.availableName = availableName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getDescripion() {
		return descripion;
	}

	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}

	public List<BaseRegionEntity> getRegionChindren() {
		return regionChindren;
	}

	public void setRegionChindren(List<BaseRegionEntity> regionChindren) {
		this.regionChindren = regionChindren;
	}
}
