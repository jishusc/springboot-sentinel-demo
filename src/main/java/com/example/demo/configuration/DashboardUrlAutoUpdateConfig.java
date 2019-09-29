package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.transport.config.TransportConfig;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

/**
 * 监听sentinel dashboard url更新
 * @author Administrator
 *
 */
@Configuration
public class DashboardUrlAutoUpdateConfig {

	@Value("${spring.cloud.sentinel.transport.dashboard:}")
	private String dashboardUrl;

	//"test_dynamic_dashboardurl" 为配置dashboard url(Apollo的key为spring.cloud.sentinel.transport.dashboard)
	// 所在的namespace名称，需要根据实际情况修改
	@ApolloConfigChangeListener("test_dynamic_dashboardurl")
	private void configChangeListener(ConfigChangeEvent changeEvent) {
		for (String changedKey : changeEvent.changedKeys()) {
			ConfigChange configChange = changeEvent.getChange(changedKey);
			if ("spring.cloud.sentinel.transport.dashboard".equals(changedKey)) {
				SentinelConfig.setConfig(TransportConfig.CONSOLE_SERVER, configChange.getNewValue());
			}
		}
	}
}
