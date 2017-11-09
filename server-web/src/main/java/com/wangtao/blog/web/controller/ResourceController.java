package com.wangtao.blog.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangtao.blog.common.constant.interfaces.ISystemBaseConstant;
import com.wangtao.blog.common.exception.blogger.BloggerException;
import com.wangtao.blog.common.response.ResponseParameterEntity;
import com.wangtao.blog.common.util.StringUtils;
import com.wangtao.blog.core.blogger.IBloggerService;
import com.wangtao.blog.core.region.IBaseRegionService;
import com.wangtao.blog.model.entity.blogger.BloggerEntity;
import com.wangtao.blog.web.base.AbstractBaseController;

/**
 * @ClassName:BloggerController
 * @author: KarlWang
 * @Description: TODO(博主控制层)
 * @date:2017年4月10日 下午4:26:31
 * @see com.wangtao.blog.web.controller.ResourceController
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractBaseController{

	private final static Logger logger = Logger.getLogger(ResourceController.class);
	
	@Autowired
	IBloggerService bloggerService;
	
	@Autowired
	IBaseRegionService baseRegionService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "admin/login";
	}
	
	/**
	 * @Title: login 
	 * @Description: TODO(登陆) 
	 * @param @param request
	 * @param @param response
	 * @param @param resp
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, 
			HttpServletResponse response, @ModelAttribute("resp") ResponseParameterEntity resp) {
		HttpSession session = request.getSession();
		Object userObj = session.getAttribute(ISystemBaseConstant.BLOGGER_LOGIN_SESSION_KEY);
		if(null != userObj) {
			try {
				response.sendRedirect("home");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			return null;
		}
		String userCode = request.getParameter("userName");
		String password = request.getParameter("password");
		String verifyCode =  request.getParameter("verifyCode");
		if(StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(verifyCode)) {
			if(request.getSession().getAttribute("verifyCode").equals(verifyCode.toLowerCase())) {
				try{
					BloggerEntity loginBlogger = bloggerService.validate(userCode,password,verifyCode);
					session.setAttribute(ISystemBaseConstant.BLOGGER_LOGIN_SESSION_KEY, loginBlogger);
					resp.setResultFlag(true);
				}catch(BloggerException e) {
					logger.error(e.getMessage(), e);
					resp.setResultFlag(false);
					resp.setMessage(e.getMessage());
				}
			} else{
				resp.setResultFlag(false);
				resp.setMessage("验证码错误！");
			}
			success(response, resp);
			return null;
		}
		return "blogger/login";
	}
	
	@RequestMapping("home")
	public String home(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("resp") ResponseParameterEntity resp) {
		String menu = "[{\"menuId\":\"10\",\"parentId\":\"0\",\"menuName\":\"系统设置\",\"menuUrl\":\"\",\"chindren\":[{\"menuId\":\"1001\",\"parentId\":null,\"menuName\":\"用户\",\"menuUrl\":\"\",\"chindren\":[{\"menuId\":\"100101\",\"parentId\":null,\"menuName\":\"普通用户\",\"menuUrl\":\"http://erp.yx.com/User\",\"chindren\":null},{\"menuId\":\"100102\",\"parentId\":null,\"menuName\":\"我的管理员\",\"menuUrl\":\"http://erp.yx.com/UserAdmin/MyIndex\",\"chindren\":null},{\"menuId\":\"100103\",\"parentId\":null,\"menuName\":\"客户管理员\",\"menuUrl\":\"http://erp.yx.com/UserAdmin\",\"chindren\":null}]},{\"menuId\":\"1002\",\"parentId\":null,\"menuName\":\"角色\",\"menuUrl\":\"\",\"chindren\":[{\"menuId\":\"100201\",\"parentId\":null,\"menuName\":\"公司角色权限\",\"menuUrl\":\"http://erp.yx.com/CompanyRole\",\"chindren\":null}]},{\"menuId\":\"1003\",\"parentId\":null,\"menuName\":\"组织架构\",\"menuUrl\":\"http://erp.yx.com/OrgUnit\",\"chindren\":null}]}]";
		request.getSession().setAttribute("menu", menu);
		return "blogger/home";
	}
	
	/**
	 * @Title: logout 
	 * @Description: TODO(注销) 
	 * @param @param request
	 * @param @param response
	 * @param @param resp
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("resp") ResponseParameterEntity resp) {
		HttpSession session = request.getSession();
		session.removeAttribute(ISystemBaseConstant.BLOGGER_LOGIN_SESSION_KEY);
		session.invalidate();
		resp.setResultFlag(true);
		success(response, resp);
		return "blogger/login";
	}
}
