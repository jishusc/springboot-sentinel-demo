package com.example.demo.configuration;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

/**
  * 资源清理(资源（比如将满足 /foo/:id 的 URL 都归到 /foo/* 资源下）)
  * 参考：https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel#%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8-sentinel 
 *      https://www.jianshu.com/p/96f5980d9798
 * @author Administrator
 *
 */
@Component
public class CustomerUrlCleaner implements UrlCleaner {

	@Override
	public String clean(String originUrl) {
		//对于/helloDelay/1,/helloDelay/2,/helloDelay/3等restful风格接口，统一按照url：/helloDelay/*处理，防止上报给sentinel dashboard的请求url太多
		if(originUrl.startsWith("/helloDelay/")) {
			return "/helloDelay/*";
		}
		if(originUrl.startsWith("/favicon.ico")) {
			return null;
		}
		return originUrl;
	}

}
