package com.wangtao.blog.common.constant.enums;

public enum BaseRegionEnum {
	
	REGION_TYPE__COUNTRY("0","country","国家级行政区"),
	
	REGION_TYPE__PROVINCE("1","province","省级行政区"),
	
	REGION_TYPE__CITY("2","city","市级行政区"),
	
	REGION_TYPE__COUNTY("3","county","区(县)级行政区"),
	
	REGION_TYPE__TOWN("4","town","乡(镇)级行政区"),
	
	REGION_TYPE__STREET("5","street","街道"),
	;
	private String regionType; // // 区域类型：country-0 、province-1、city-2、county-3、town-4、street-5
	
	private String degree; // 级别：country-国家 、province-省、city-市、county-县、town-乡镇、street-街道
	
	private String desc;	// 描述
	
	private BaseRegionEnum(String regionType,String degree,String desc) {
		this.regionType = regionType;
		this.degree = degree;
		this.desc = desc;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
