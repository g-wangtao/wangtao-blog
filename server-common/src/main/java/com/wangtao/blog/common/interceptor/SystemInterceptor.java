package com.wangtao.blog.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wangtao.blog.common.properties.SystemGlobal;
import com.wangtao.blog.common.util.IPInfoUtil;
import com.wangtao.blog.common.util.MobileRequestUtil;
import com.wangtao.blog.common.util.StringUtils;

/**
 * @ClassName:SystemInterceptor
 * @author: KarlWang
 * @Description: TODO(系统拦截器) 
 * @date:2017年6月12日 下午2:57:14
 * @see com.wangtao.blog.common.interceptor.SystemInterceptor
 */
public class SystemInterceptor implements HandlerInterceptor {
	
	@Resource
	SystemGlobal global;
	
	/**
	 * @Title: preHandle 
	 * @Description: TODO(在业务处理器处理请求之前被调用。)
	 * @Description：TODO(如果返回false,从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链) 	
	 * @Description: TODO(如果返回true,执行下一个拦截器,直到所有的拦截器都执行完毕,再执行被拦截的Controller,然后进入拦截器链) 
	 * @Description: TODO(从最后一个拦截器往回执行所有的postHandle(),接着再从最后一个拦截器往回执行所有的afterCompletion()) 
	 * @param request
	 * @param response
	 * @param handler
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		StringBuffer contentPath = new StringBuffer();
		contentPath.append(
				request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()+"/");
		// 工程名称
		String endPath = global.getProperties().getProperty("content.end.path"); // 兼容特殊情况需要改变根路径
		request.setAttribute("contentPath", contentPath.toString() + (StringUtils.isNotBlank(endPath) ? endPath : ""));
		// 应用环境名称
		request.setAttribute("blogEnv", global.getProperties().getProperty("blog.env"));
		// 获取请求设备信息
		request.setAttribute("isPcBrower", MobileRequestUtil.getDifferenceRequestTypeStr(request));
		// 设置请求IP地址
		setRequestAddress(request);
		return true;
	}
	
	/**
	 * @Title: afterCompletion 
	 * @Description: TODO(全部处理完处理) 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	/**
	 * @Title: postHandle 
	 * @Description: TODO(拦截后处理) 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView)
			throws Exception {
	}
	
	/**
	 * @Title: setRequestAddress 
	 * @Description: TODO(设置请求IP地址) 
	 * @param @param request 设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	private void setRequestAddress(HttpServletRequest request) {
		
		// 获取客户端请求最外层IP地址
		String remoteAddr = IPInfoUtil.getClientReqAddr(request);
		request.setAttribute("remoteEndAddr", remoteAddr);
		
		// 获取客户端请求最里层IP地址
		request.setAttribute("remoteStartAddr", request.getRemoteAddr());

		// 获取客户端请求本地IP地址
		request.setAttribute("remoteLocalAddr", request.getLocalAddr());
	}

}