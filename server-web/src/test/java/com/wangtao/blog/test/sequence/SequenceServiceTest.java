package com.wangtao.blog.test.sequence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.core.ISequenceService;

/**
 * @ClassName:SequenceServiceTest
 * @author: KarlWang
 * @Description: TODO(序列测试类) 
 * @date:2017年9月15日 上午11:58:49
 * @see com.wangtao.blog.test.sequence.SequenceServiceTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })

public class SequenceServiceTest {

	@Autowired
	private ISequenceService sequenceService;
	
	@Test
	public void a() {
		System.out.println(sequenceService.getNextValue("test1", 1));
	}

}