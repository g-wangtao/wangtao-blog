package com.wangtao.blog.dao.blogger;

import com.wangtao.blog.model.entity.blogger.BloggerEntity;

/**
 * @ClassName:IBloggerDao
 * @author: KarlWang
 * @Description: TODO(博主DAO) 
 * @date:2017年5月3日 下午4:49:43
 * @see com.wangtao.blog.dao.blogger.IBloggerDao
 */
public interface IBloggerDao {
	
	BloggerEntity queryByUserName(String userName);
	
	int saveByEntity (BloggerEntity entity);
}
