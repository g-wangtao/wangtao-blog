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
			e.printStackTrace();
		}
	}

	/**
	 * 设置单次请求是否成功的状态
	 */
	public void setReqAttSucess(HttpServletRequest request, String status) {
		request.setAttribute("success", status);
	}

	/**
	 * 重定向页面
	 */
	public String redirectTo(String url) {
		WebContext.get().setRequestRedirect();
		return "redirect:" + url;
	}

	/**
	 * Servlet上下文
	 */
	public ServletContext findServletContext() {
		return WebContext.getServletContext();
	}

	/**
	 * 获取Servlet真实路径
	 */
	public String findServletRealPath(String path) {
		return this.findServletContext().getRealPath(path);
	}

	/**
	 * 设置Web数据值
	 */
	public AbstractBaseController setWebData(String key, Object value) {
		WebContext.get().getData().put(key, value);
		return this;
	}

	/**
	 * 设置JSON请求标志
	 */
	public AbstractBaseController setRequestJSON() {
		WebContext.get().setRequestJSON();
		return this;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年8月11日 上午10:20:12
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年8月11日 上午10:20:12
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年8月11日 上午10:20:12
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 作者：ZOUYONG
	 * 时间：2016年8月11日 上午10:20:12
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

}
