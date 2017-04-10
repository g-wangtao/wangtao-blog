package com.wangtao.blog.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:StringUtils
 * @author: KarlWang
 * @Description: TODO(字符串工具类) 
 * @date:2017年4月10日 下午3:54:54
 * @see com.wangtao.blog.common.util.StringUtils
 */
public class StringUtils {

	/**
	 * @Title: isBlank 
	 * @Description: TODO(判断是否是空) 
	 * @param @param str
	 * @param @return 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	public static boolean isBlank(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Title: isNotBlank 
	 * @Description: TODO(判断是否不是空) 
	 * @param @param str
	 * @param @return 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	public static boolean isNotBlank(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Title: formatLike 
	 * @Description: TODO(格式化模糊查询) 
	 * @param @param str
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String formatLike(String str){
		if(isNotBlank(str)){
			return "%"+str+"%";
		}else{
			return null;
		}
	}
	
	/**
	 * @Title: filterWhite 
	 * @Description: TODO(过滤掉集合里的空格) 
	 * @param @param list
	 * @param @return 设定文件 
	 * @return List<String> 返回类型 
	 * @throws
	 */
	public static List<String> filterWhite(List<String> list){
		List<String> resultList=new ArrayList<String>();
		for(String l:list){
			if(isNotBlank(l)){
				resultList.add(l);
			}
		}
		return resultList;
	}

}
