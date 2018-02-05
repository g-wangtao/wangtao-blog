package com.wangtao.blog.common.filter;

import com.wangtao.blog.common.constant.interfaces.ISystemBaseConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName:CommonFilter
 * @author: KarlWang
 * @Description: TODO(系统层过滤器) 
 * @date:2017年6月5日 下午12:03:09
 * @see com.wangtao.blog.common.filter.CommonFilter
 */
public class CommonFilter extends DefaultFilter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	/**
	 * 设置应用信息
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 * @since:0.6
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = null;
		if (request instanceof HttpServletRequest) {
			req = (HttpServletRequest) request;
		}
		HttpServletResponse resp = null;
		if (response instanceof HttpServletResponse) {
			resp = (HttpServletResponse) response;
		}
		
		// 请求的uri
		String uri = req.getRequestURI();
		// String uri = req.getServletPath();
		// 不过滤的uri
		String[] notFilter = new String[] { "/blogger/login", "/test/query","/verifyCode/create","/static/","/index"};
		
		HttpSession session = req.getSession();
		Object userObj = session.getAttribute(ISystemBaseConstant.BLOGGER_LOGIN_SESSION_KEY);
		if(null == userObj) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
					doFilter = false;
					break;
				}
			}
			if(doFilter) {
				resp.sendRedirect(req.getContextPath() + "/blogger/login");
				return;
			}
		}
		
		try {
			super.doFilter(request, response, filterChain);
		} finally {
			
		}
	}
}
