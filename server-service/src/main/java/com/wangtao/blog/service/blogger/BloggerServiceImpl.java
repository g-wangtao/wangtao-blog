package com.wangtao.blog.service.blogger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangtao.blog.common.constant.interfaces.ISystemBaseConstant;
import com.wangtao.blog.common.exception.BusinessException;
import com.wangtao.blog.common.exception.blogger.BloggerException;
import com.wangtao.blog.common.util.StringUtils;
import com.wangtao.blog.common.util.md5.CryptoUtil;
import com.wangtao.blog.core.blogger.IBloggerService;
import com.wangtao.blog.dao.blogger.IBloggerDao;
import com.wangtao.blog.model.entity.blogger.BloggerEntity;

/**
 * @ClassName:BloggerServiceImpl
 * @author: KarlWang
 * @Description: TODO(博主业务实现层)
 * @date:2017年5月3日 下午5:06:19
 * @see com.wangtao.blog.service.blogger.BloggerServiceImpl
 */
@Service
public class BloggerServiceImpl implements IBloggerService {

	@Resource
	IBloggerDao bloggerDao;

	@Override
	public BloggerEntity queryByUserCode(String userCode) {
		if (StringUtils.isBlank(userCode)) {
			return null;
		}
		return bloggerDao.queryByUserCode(userCode);
	}

	@Override
	public boolean saveByEntity(BloggerEntity entity) {
		if(null == entity) {
			return false;
		}
		return (bloggerDao.saveByEntity(entity) == 1);
	}
	
	@Override
	public BloggerEntity validate(String userCode, String password,String verifyCode) throws BusinessException {
		if (StringUtils.isBlank(userCode)) {
			throw new BloggerException("登陆账号不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			throw new BloggerException("登陆密码不能为空！");
		}
		BloggerEntity blogger = this.queryByUserCode(userCode);
		if (null == blogger) {
			throw new BloggerException("账号不存在！");
		}
		if (!(blogger.getPassword().equals(CryptoUtil.digestByEncode(password)))) {
			throw new BloggerException("密码错误！");
		}
		if (blogger.getActive() != ISystemBaseConstant.ACTIVE) {
			throw new BloggerException("用户已冻结！");
		}
		return blogger;
	}

	@Override
	public String getMenuList() {
		String m = "[\n" +
				"  {\n" +
				"    \"menuId\": \"10\",\n" +
				"    \"parentId\": \"0\",\n" +
				"    \"menuName\": \"系统设置\",\n" +
				"    \"menuUrl\": \"\",\n" +
				"    \"chindren\": [\n" +
				"      {\n" +
				"        \"menuId\": \"1001\",\n" +
				"        \"parentId\": null,\n" +
				"        \"menuName\": \"用户\",\n" +
				"        \"menuUrl\": \"\",\n" +
				"        \"chindren\": [\n" +
				"          {\n" +
				"            \"menuId\": \"100101\",\n" +
				"            \"parentId\": null,\n" +
				"            \"menuName\": \"普通用户\",\n" +
				"            \"menuUrl\": \"http://erp.yx.com/User\",\n" +
				"            \"chindren\": null\n" +
				"          },\n" +
				"          {\n" +
				"            \"menuId\": \"100102\",\n" +
				"            \"parentId\": null,\n" +
				"            \"menuName\": \"我的管理员\",\n" +
				"            \"menuUrl\": \"http://erp.yx.com/UserAdmin/MyIndex\",\n" +
				"            \"chindren\": null\n" +
				"          },\n" +
				"          {\n" +
				"            \"menuId\": \"100103\",\n" +
				"            \"parentId\": null,\n" +
				"            \"menuName\": \"客户管理员\",\n" +
				"            \"menuUrl\": \"http://erp.yx.com/UserAdmin\",\n" +
				"            \"chindren\": null\n" +
				"          }\n" +
				"        ]\n" +
				"      },\n" +
				"      {\n" +
				"        \"menuId\": \"1002\",\n" +
				"        \"parentId\": null,\n" +
				"        \"menuName\": \"角色\",\n" +
				"        \"menuUrl\": \"\",\n" +
				"        \"chindren\": [\n" +
				"          {\n" +
				"            \"menuId\": \"100201\",\n" +
				"            \"parentId\": null,\n" +
				"            \"menuName\": \"公司角色权限\",\n" +
				"            \"menuUrl\": \"http://erp.yx.com/CompanyRole\",\n" +
				"            \"chindren\": null\n" +
				"          }\n" +
				"        ]\n" +
				"      },\n" +
				"      {\n" +
				"        \"menuId\": \"1003\",\n" +
				"        \"parentId\": null,\n" +
				"        \"menuName\": \"组织架构\",\n" +
				"        \"menuUrl\": \"http://erp.yx.com/OrgUnit\",\n" +
				"        \"chindren\": null\n" +
				"      }\n" +
				"    ]\n" +
				"  },\n" +
				"  {\n" +
				"    \"menuId\": \"20\",\n" +
				"    \"parentId\": \"0\",\n" +
				"    \"menuName\": \"博客管理\",\n" +
				"    \"menuUrl\": \"\",\n" +
				"    \"chindren\": [\n" +
				"      {\n" +
				"        \"menuId\": \"2004\",\n" +
				"        \"parentId\": null,\n" +
				"        \"menuName\": \"友情链接\",\n" +
				"        \"menuUrl\": \"http://erp.yx.com/OrgUnit\",\n" +
				"        \"chindren\": null\n" +
				"      }\n" +
				"    ]\n" +
				"  }\n" +
				"]";
		return m;
	}

}
