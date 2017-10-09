package com.wangtao.blog.core;

/**
 * @ClassName:ISequenceService
 * @author: KarlWang
 * @Description: TODO(序列Service层) 
 * @date:2017年7月21日 下午4:19:44
 * @see com.wangtao.blog.core.ISequenceService
 */
public interface ISequenceService {
	
	/**
	 * @Title: getNextValue 
	 * @Description: TODO(获取下一个序列) 
	 * @param seqName
	 * @param count
	 * @return long 返回类型 
	 * @throws
	 */
	long getNextValue(String seqName, int count);
	
	/**
	 * @Title: count 
	 * @Description: TODO(根据名称获取总数) 
	 * @param seqName
	 * @return long 返回类型 
	 * @throws
	 */
	long count(String seqName);
}
