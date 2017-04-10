package com.wangtao.blog.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:DateUtil
 * @author: KarlWang
 * @Description:日期工具类
 * @date:2017年4月5日 下午12:02:07
 * @see com.wangtao.blog.common.util.DateUtil
 */
public class DateUtil {
	

	/**
	 * @Title: formatDate 
	 * @Description: TODO(日期对象转字符串) 
	 * @param @param date
	 * @param @param format
	 * @param @return 日期字符
	 * @return String 返回类型 
	 * @throws
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 字符串转日期对象
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtils.isBlank(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
	
}
