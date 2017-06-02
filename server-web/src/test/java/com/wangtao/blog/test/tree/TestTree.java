package com.wangtao.blog.test.tree;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangtao.blog.core.region.IBaseRegionService;
import com.wangtao.blog.model.entity.region.BaseRegionEntity;

/**
 * @ClassName:TestTree
 * @author: KarlWang
 * @Description: TODO(测试java树) 
 * @date:2017年5月29日 下午1:56:58
 * @see com.wangtao.blog.test.tree.TestTree
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:datasource/datasource.xml"})
public class TestTree {
	
	@Resource
	IBaseRegionService baseRegionService;
	
	@Test
	public void treeTest() {
		List<BaseRegionEntity> regionTree= baseRegionService.baseRegionTree("1");
		System.out.println(regionTree);
	}
}
