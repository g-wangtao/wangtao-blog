package com.wangtao.blog.common.load;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * @ClassName:ApplicationPropertiesFactory
 * @author: KarlWang
 * @Description: TODO(容器启动属性加载支持类)
 * @date:2017年5月17日 下午4:32:49
 * @see com.wangtao.blog.common.load.ApplicationPropertiesFactory
 */
public class ApplicationPropertiesFactory extends PropertiesLoaderSupport
		implements FactoryBean<Properties>, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(ApplicationPropertiesFactory.class);

	private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	public static Properties properties;

	private String env; // 环境变量

	@Override
	public void afterPropertiesSet() throws IOException {

		// 获取要加载的properties文件
		// setLocations(resolver.getResources("classpath*:/env/" + env + // "/**"));
		
		setLocations(resolver.getResources(env));

		// 默认设置找不到资源也不会报错
		this.setIgnoreResourceNotFound(true);

		// 加载properties
		properties = super.mergeProperties();

		logger.info("当前系统环境为：" + properties.getProperty("blog.env"));
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public Properties getObject() throws IOException {
		return properties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	/**
	 * @return the env
	 */
	public String getEnv() {
		return env;
	}

	/**
	 * @param env 
	 * the env to set
	 */
	public void setEnv(String env) {
		this.env = env;
	}

}
