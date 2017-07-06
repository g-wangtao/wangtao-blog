package com.wangtao.blog.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName:DefaultFilter
 * @author: KarlWang
 * @Description: TODO(系统过滤器基类；filte默认实现) 
 * @date:2017年6月5日 下午12:05:44
 * @see com.wangtao.blog.common.filter.DefaultFilter
 */
public class DefaultFilter implements Filter {
    
    @Override
    public void destroy() {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
    	filterChain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
}

