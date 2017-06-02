package com.wangtao.blog.core.region;

import java.util.List;

import com.wangtao.blog.model.entity.region.BaseRegionEntity;

/**
 * @ClassName:IBaseRegionService
 * @author: KarlWang
 * @Description: TODO(基础区域Service) 
 * @date:2017年5月29日 下午2:57:25
 * @see com.wangtao.blog.core.region.IBaseRegionService
 */
public interface IBaseRegionService {
	
	public List<BaseRegionEntity> baseRegionTree(String regionType);
	
}
