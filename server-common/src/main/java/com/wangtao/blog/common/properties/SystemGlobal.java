package com.wangtao.blog.common.properties;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:SystemGlobal
 * @author: KarlWang
 * @Description: TODO(平台与Spring解析的属性配置对象) 
 * @date:2017年5月17日 下午4:42:03
 * @see com.wangtao.blog.common.properties.SystemGlobal
 */
@Component("global")
public class SystemGlobal {

	@Value("#{configProperties}")
	private Properties properties;

	public String getSxbbsGlobal(String key) {
		return (String) properties.get(key);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
