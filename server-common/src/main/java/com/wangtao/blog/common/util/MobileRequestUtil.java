package com.wangtao.blog.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName:MobileRequestUtil
 * @author: KarlWang
 * @Description: TODO(处理不同设备请求的工具类) 
 * @date:2017年6月12日 下午5:58:40
 * @see com.wangtao.blog.common.util.MobileRequestUtil
 */
public class MobileRequestUtil {
	
	/**
	 * 默认请求设备型号：pc端浏览器
	 */
	public static String defaultiBrower = "web";
	
	/**
	 * 手机浏览器请求头类型
	 */
	public static String[] deviceArray = new String[]{"android", "iphone", "symbianos", "windows phone", "ipad", "ipod"};
	
	/**
	 * @Title: getDifferenceRequestTypeStr 
	 * @Description: TODO(获取不同设备请求的设备型号) 
	 * @param @param request
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String getDifferenceRequestTypeStr(HttpServletRequest request) {

		String isPcBrower = defaultiBrower;
		// 获取请求头信息
		String requestHeader = request.getHeader("user-agent");
		if(StringUtils.isNotBlank(requestHeader)) {
			requestHeader = requestHeader.toLowerCase();
			for(int i=0; i<deviceArray.length; i++) {
	            if(requestHeader.indexOf(deviceArray[i])>0) {
	                isPcBrower = deviceArray[i];
	                break;
	            }
	        }
		}
		return isPcBrower;
	}
}
