package com.wangtao.blog.web.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * @ClassName:WebContext
 * @author: KarlWang
 * @Description: TODO(Web请求上下文信息) 
 * @date:2017年6月2日 下午6:52:39
 * @see com.wangtao.blog.web.context.WebContext
 */
public final class WebContext {

	/** JSON请求标志 */
	public static final String JSON_FLAG_KEY = "json_flag";

	/** Redirect标志 */
	public static final String REDIRECT_FLAG_KEY = "redirect_flag";

	/** 静态服务器 */
	public static final String IMAGE_HOST_KEY = "ictx";

	/** Web请求容器 */
	private static final ThreadLocal<WebContext> _holder = new ThreadLocal<WebContext>();

	/** 容器配置 */
	private static ServletConfig config;

	/** Web请求对象 */
	private final HttpServletRequest request;

	/** Web返回对象 */
	private final HttpServletResponse response;

	/** Web请求数据值 */
	private final Map<String, Object> data;

	/**
	 * 初始化
	 */
	public static final void init(ServletConfig _config) {
		WebContext.config = _config;
	}

	/**
	 * 检查是否初始化完成
	 */
	public static final boolean isReady() {
		return (getServletConfig() != null);
	}

	/**
	 * CTOR
	 */
	public WebContext(HttpServletRequest request, HttpServletResponse response) {
		Assert.notNull(request, "请求对象[HttpServletRequest]为NULL！");
		Assert.notNull(response, "返回对象[HttpServletResponse]为NULL！");

		this.data = new ConcurrentHashMap<String, Object>();
		this.request = request;
		this.response = response;
	}

	/**
	 * 获取Web请求数据值
	 */
	public Map<String, Object> getData() {
		return this.data;
	}

	/**
	 * 设置JSON请求标志
	 */
	public void setRequestJSON() {
		this.setRequestJSON(true);
	}

	/**
	 * 设置JSON请求标志
	 */
	public void setRequestJSON(boolean flag) {
		this.getData().put(JSON_FLAG_KEY, flag);
	}

	/**
	 * 设置Redirect标志
	 */
	public void setRequestRedirect() {
		this.setRequestRedirect(true);
	}

	/**
	 * 设置Redirect标志
	 */
	public void setRequestRedirect(boolean flag) {
		this.getData().put(REDIRECT_FLAG_KEY, flag);
	}

	/**
	 * 判断JSON请求标志
	 */
	public boolean isRequestJSON() {
		return BooleanUtils.toBoolean(String.valueOf(this.getData().get(JSON_FLAG_KEY)));
	}

	/**
	 * 判断Redirect标志
	 */
	public boolean isRequestRedirect() {
		return BooleanUtils.toBoolean(String.valueOf(this.getData().get(REDIRECT_FLAG_KEY)));
	}

	/**
	 * 获取静态服务器
	 */
	public static String getImageHostCtx() {
		String ictx = (String) getServletContext().getAttribute(IMAGE_HOST_KEY);
		if (StringUtils.isBlank(ictx)) {
			ictx = getServletContext().getContextPath();
		}

		return ictx;
	}

	/**
	 * 设置静态服务器
	 */
	public static void setImageHostCtx(String ictx) {
		if (StringUtils.isBlank(ictx)) {
			ictx = getServletContext().getContextPath();
		}

		getServletContext().setAttribute(IMAGE_HOST_KEY, ictx);
	}

	/**
	 * 获取请求对象
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 取得会话信息[新建]
	 */
	public HttpSession getSession() {
		return this.getSession(true);
	}

	/**
	 * 取得会话信息
	 */
	public HttpSession getSession(boolean create) {
		return this.request.getSession(create);
	}

	/**
	 * 获取返回对象
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取容器配置
	 */
	public static ServletConfig getServletConfig() {
		return WebContext.config;
	}

	/**
	 * 获取容器上下文
	 */
	public static ServletContext getServletContext() {
		return getServletConfig().getServletContext();
	}

	/**
	 * 设置上下文信息
	 */
	public static final void set(WebContext context) {
		Assert.notNull(context, "Web请求上下文信息[WebContext]为NULL！");
		_holder.set(context);
	}

	/**
	 * 获取上下文信息
	 */
	public static final WebContext get() {
		return _holder.get();
	}

	/**
	 * 清理上下文信息
	 */
	public static final void remove() {
		_holder.remove();
	}

}
