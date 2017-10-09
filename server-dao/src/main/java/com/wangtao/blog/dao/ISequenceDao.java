package com.wangtao.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:ISequenceDao
 * @author: KarlWang
 * @Description: TODO(序列DAO层) 
 * @date:2017年7月21日 下午3:35:28
 * @see com.wangtao.blog.dao.ISequenceDao
 */
@Repository
public interface ISequenceDao 
{
	long selectNextIdBySeqName(@Param("seqName") String seqName, @Param("idCount") int count);
	
	int count(@Param("seqName") String seqName);
}
