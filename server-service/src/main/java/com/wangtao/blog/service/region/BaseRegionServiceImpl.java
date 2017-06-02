package com.wangtao.blog.service.region;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wangtao.blog.common.util.StringUtils;
import com.wangtao.blog.core.region.IBaseRegionService;
import com.wangtao.blog.dao.region.IBaseRegionDao;
import com.wangtao.blog.model.entity.region.BaseRegionEntity;

/**
 * @ClassName:BaseRegionServiceImpl
 * @author: KarlWang
 * @Description: TODO(系统基础行政区域Service实现) 
 * @date:2017年6月1日 下午6:02:25
 * @see com.wangtao.blog.service.region.BaseRegionServiceImpl
 */
@Service
public class BaseRegionServiceImpl implements IBaseRegionService {
	
	private int  i =  0;
	
	@Resource
	IBaseRegionDao baseRegionDao;
	
	/**
	 * @Title: baseRegionTree 
	 * @Description: TODO() 
	 * @param @param regionType
	 * @return List<BaseRegionEntity> 返回类型 
	 * @throws
	 */
	@Override
	public List<BaseRegionEntity> baseRegionTree(String regionType) {
		if(StringUtils.isBlank(regionType)) {
			return null;
		}
		List<BaseRegionEntity> baseRegionTree = this.baseRegionDao.queryByRegionType(regionType);
		if (!CollectionUtils.isEmpty(baseRegionTree)) {
			for (BaseRegionEntity regionTree : baseRegionTree) {
				++ i;
				this.setTreeChild(regionTree);
			}
		}
		return baseRegionTree;
	}
	
	/**
	 * @Title: setTreeChild 
	 * @Description: TODO() 
	 * @param @param regionTree
	 * @param @param baseRegions 设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	private void setTreeChild(BaseRegionEntity regionTree) {
		try{
			System.out.println("????????");
			List<BaseRegionEntity> treeChild = this.baseRegionDao.queryByParentCode(regionTree.getRegionCode());
			if(!CollectionUtils.isEmpty(treeChild)) {
				regionTree.setRegionChindren(treeChild);
				for(BaseRegionEntity child : treeChild) {
					++ i;
					this.setTreeChild(child);
				}
			}
		}catch (StackOverflowError e) {
			System.out.println(e.getMessage());
			System.out.println("==============================" + i + "===========================================");
		}
	}
}
