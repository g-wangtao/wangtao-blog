package com.wangtao.blog.web.base;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wangtao.blog.web.context.WebContext;

/**
 * @ClassName:AbstractBaseController
 * @author: KarlWang
 * @Description: TODO(所有控制类的基类)
 * @date:2017年6月2日 下午5:40:02
 * @see com.wangtao.blog.web.base.AbstractBaseController
 */
public abstract class AbstractBaseController {

	private static final Logger logger = Logger.getLogger(AbstractBaseController.class);

	/**
	 * 分页最大记录数
	 */
	protected int limit;

	/**
	 * 分页开始记录数
	 */
	protected int start;

	/**
	 * 请求Response输出
	 */
	public void success(HttpServletResponse response, Object result) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String respStr = new Gson().toJson(result);
			logger.info("传出参数：" + respStr);
			response.getWriter().print(respStr);
		} catch (IOException e) {
			logger.info("请求返回reponse输出发生异常！message：" + result);
		}
	}

	/**
	 * @Title: setReqAttSucess 
	 * @Description: TODO(设置单次请求是否成功的状态) 
	 * @param @param request
	 * @param @param status 设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	public void setReqAttSucess(HttpServletRequest request, String status) {
		request.setAttribute("success", status);
	}

	/**
	 * @Title: redirectTo 
	 * @Description: TODO(重定向页面) 
	 * @param @param url
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String redirectTo(String url) {
		WebContext.get().setRequestRedirect();
		return "redirect:" + url;
	}

	/**
	 * @Title: findServletContext 
	 * @Description: TODO(Servlet上下文) 
	 * @param @return 设定文件 
	 * @return ServletContext 返回类型 
	 * @throws
	 */
	public ServletContext findServletContext() {
		return WebContext.getServletContext();
	}

	/**
	 * @Title: findServletRealPath 
	 * @Description: TODO(获取Servlet真实路径) 
	 * @param @param path
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String findServletRealPath(String path) {
		return this.findServletContext().getRealPath(path);
	}

	/**
	 * @Title: setWebData 
	 * @Description: TODO(设置Web数据值) 
	 * @param @param key
	 * @param @param value
	 * @param @return 设定文件 
	 * @return AbstractBaseController 返回类型 
	 * @throws
	 */
	public AbstractBaseController setWebData(String key, Object value) {
		WebContext.get().getData().put(key, value);
		return this;
	}

	/**
	 * @Title: setRequestJSON 
	 * @Description: TODO(设置JSON请求标志) 
	 * @param @return 设定文件 
	 * @return AbstractBaseController 返回类型 
	 * @throws
	 */
	public AbstractBaseController setRequestJSON() {
		WebContext.get().setRequestJSON();
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
