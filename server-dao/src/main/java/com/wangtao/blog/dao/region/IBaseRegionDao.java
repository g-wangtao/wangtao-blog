package com.wangtao.blog.dao.region;

import java.util.List;

import com.wangtao.blog.model.entity.region.BaseRegionEntity;

/**
 * @ClassName:BaseRegionDao
 * @author: KarlWang
 * @Description: TODO(基础区域DAO) 
 * @date:2017年5月29日 下午2:03:55
 * @see com.wangtao.blog.dao.region.IBaseRegionDao
 */
public interface IBaseRegionDao {
	
	/**
	 * @Title: queryByRegionCode 
	 * @Description: TODO(根据行政区编码查询行政区) 
	 * @param @param regionCode
	 * @param @return 设定文件 
	 * @return List<BaseRegionEntity> 返回类型 
	 * @throws
	 */
	public List<BaseRegionEntity> queryByRegionCode(String regionCode);
	
	/**
	 * @Title: queryByParentCode 
	 * @Description: TODO(根据父级行政区编码查询行政区) 
	 * @param @param parentCode
	 * @param @return 设定文件 
	 * @return List<BaseRegionEntity> 返回类型 
	 * @throws
	 */
	public List<BaseRegionEntity> queryByParentCode(String parentCode);
	
	/**
	 * @Title: queryByRegionType 
	 * @Description: TODO(根据行政区级别查询行政区) 
	 * @param @param regionType
	 * @param @return 设定文件 
	 * @return List<BaseRegionEntity> 返回类型 
	 * @throws
	 */
	public List<BaseRegionEntity> queryByRegionType(String regionType); 
	
}
