package com.wangtao.blog.core.blogger;

import com.wangtao.blog.model.entity.blogger.BloggerEntity;

/**
 * @ClassName:IBloggerService
 * @author: KarlWang
 * @Description: TODO(博主业务接口层) 
 * @date:2017年5月3日 下午5:00:20
 * @see com.wangtao.blog.core.blogger.IBloggerService
 */
public interface IBloggerService {
	
	BloggerEntity queryByUserCode(String userCode);
	
	boolean saveByEntity (BloggerEntity entity);
	
	BloggerEntity validate(String account, String password, String verifyCode);
	
	String getMenuList();
}
