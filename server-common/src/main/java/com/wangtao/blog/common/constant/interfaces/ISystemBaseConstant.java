package com.wangtao.blog.common.constant.interfaces;

/**
 * @ClassName:SystemBaseConstant
 * @author: KarlWang
 * @Description: TODO(系统基础常量)
 * @date:2017年5月4日 下午3:44:45
 * @see com.wangtao.blog.common.constant.interfaces.SystemBaseConstant
 */
public interface ISystemBaseConstant {
	/** 生效 */
	public static final char ACTIVE = 'Y';
	/** 未生效 */
	public static final char INACTIVE = 'N';

	/** 国  */
	public static final String REGION_TYPE_COUNTRY = "country";
	/** 省  */
	public static final String REGION_TYPE_PROVINCE = "province";
	/** 市  */
	public static final String REGION_TYPE_CITY = "city";
	/** 区  */
	public static final String REGION_TYPE_COUNTY = "county";
	/** 县  */
	public static final String REGION_TYPE_TOWN = "town";
	/** 街道 */
	public static final String REGION_TYPE_STREET = "street";

}
