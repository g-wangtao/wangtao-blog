package com.wangtao.blog.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangtao.blog.core.ISequenceService;
import com.wangtao.blog.dao.ISequenceDao;

/**
 * @ClassName:SequenceServiceImpl
 * @author: KarlWang
 * @Description: TODO(序列Service层) 
 * @date:2017年9月15日 上午11:35:22
 * @see com.wangtao.blog.service.SequenceServiceImpl
 */
@Service
public class SequenceServiceImpl implements ISequenceService {
	
	@Resource
	private ISequenceDao sequenceDao;
	
	@Override
	public long getNextValue(String seqName, int count) {
		
		return sequenceDao.selectNextIdBySeqName(seqName, count);
	}

	@Override
	public long count(String seqName) {
		return sequenceDao.count(seqName);
	}

}
