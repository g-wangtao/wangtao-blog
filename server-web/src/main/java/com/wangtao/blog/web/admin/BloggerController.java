package com.wangtao.blog.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangtao.blog.common.exception.BusinessException;
import com.wangtao.blog.core.blogger.IBloggerService;
import com.wangtao.blog.core.region.IBaseRegionService;
import com.wangtao.blog.model.entity.blogger.BloggerEntity;
import com.wangtao.blog.model.entity.region.BaseRegionEntity;

/**
 * @ClassName:BloggerController
 * @author: KarlWang
 * @Description: TODO(博主控制层)
 * @date:2017年4月10日 下午4:26:31
 * @see com.wangtao.blog.web.admin.BloggerController
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	private final static Logger logger = Logger.getLogger(BloggerController.class);
	
	@Autowired
	IBloggerService bloggerService;
	
	@Autowired
	IBaseRegionService baseRegionService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "admin/login";
	}
	
	@RequestMapping("/login")
	public String login(BloggerEntity blogger, HttpServletRequest request) {
		logger.info("博主登陆..........");
		try{
			BloggerEntity loginBlogger = bloggerService.validate(blogger.getUserName(),blogger.getPassword());
			request.setAttribute("blogger", loginBlogger);
		}catch(BusinessException be) {
			logger.info(be.getMessage(), be);
			request.setAttribute("errorInfo", be.getMessage());
			return "admin/login";
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			request.setAttribute("errorInfo", "系统未知异常！");
			return "admin/login";
		}
		return "admin/login";
	}
	
	@RequestMapping("/loginTest")
	@ResponseBody
	public String loginTest() {
		List<BaseRegionEntity> regionTree= baseRegionService.baseRegionTree();
		System.out.println(regionTree);
		logger.info("loging test ================================");
		return "login";
	}
}
