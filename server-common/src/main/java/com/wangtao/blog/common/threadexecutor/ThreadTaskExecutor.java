package com.wangtao.blog.common.threadexecutor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @ClassName:ThreadTaskExecutor
 * @author: KarlWang
 * @Description: TODO(异步处理线程管理类) 
 * @date:2017年4月12日 下午4:38:01
 * @see com.wangtao.blog.common.threadexecutor.ThreadTaskExecutor
 */
public class ThreadTaskExecutor implements InitializingBean {
	
	private final Logger logger = Logger.getLogger(ThreadTaskExecutor.class);
	
	private ThreadPoolTaskExecutor threadPool;
	
	// 初始线程数
	private int corePoolSize;
	
	// 最大线程数
	private int maxPoolSize;
	
	// 是否后台线程
	private boolean daemon;
	
	// 线程存储时间，单位：s
	private int keepAliveSeconds;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化系统线程池！");
		// 创建线程池
		this.threadPool = new ThreadPoolTaskExecutor();
		this.threadPool.setCorePoolSize(corePoolSize);
		this.threadPool.setMaxPoolSize(maxPoolSize);
		this.threadPool.setDaemon(daemon);
		this.threadPool.setKeepAliveSeconds(keepAliveSeconds);
		this.threadPool.afterPropertiesSet();
		logger.info("结束初始化系统线程池！");
	}


	public ThreadPoolTaskExecutor getThreadPool() {
		return threadPool;
	}


	public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
		this.threadPool = threadPool;
	}


	public int getCorePoolSize() {
		return corePoolSize;
	}


	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}


	public int getMaxPoolSize() {
		return maxPoolSize;
	}


	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}


	public boolean isDaemon() {
		return daemon;
	}


	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}


	public int getKeepAliveSeconds() {
		return keepAliveSeconds;
	}


	public void setKeepAliveSeconds(int keepAliveSeconds) {
		this.keepAliveSeconds = keepAliveSeconds;
	}

}
