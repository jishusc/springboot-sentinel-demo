package com.example.demo.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

/**
 * 资源清理(资源（比如将满足 /foo/:id 的 URL 都归到 /foo/* 资源下）)
 * 参考     https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel#%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8-sentinel
 *       https://www.jianshu.com/p/96f5980d9798
 *
 */
@Component
public class CustomerUrlCleaner implements UrlCleaner {

	/**
	 * 配置需要清理的url前缀（逗号分隔）
	 * 可以配置到apollo中，如/basicInfo/searchEmployeeById/,/foo/
	 */
	@Value("#{'${cleaner.url:}'.split(',')}")
    private List<String> cleanerUrlList;

    private String getCleaner(String originUrl) {
        if (cleanerUrlList != null && cleanerUrlList.size() > 0) {
            for (String s : cleanerUrlList) {
                if (s.length() > 0 && originUrl.startsWith(s)) {
                    return s + "*";
                }
            }
        }
        return originUrl;
    }
    
	@Override
	public String clean(String originUrl) {
		return getCleaner(originUrl);
	}

}
