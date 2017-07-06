package com.wangtao.blog.common.util;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName:IPInfoUtil
 * @author: KarlWang
 * @Description: TODO(IP操作工具类) 
 * @date:2017年6月12日 下午5:55:52
 * @see com.wangtao.blog.common.util.IPInfoUtil
 */
public class IPInfoUtil {

	/**
	 * @Title: getClientReqAddr 
	 * @Description: TODO(获取客户端请求IP地址) 
	 * @param @param request
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String getClientReqAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
		return ip;
	}

}
