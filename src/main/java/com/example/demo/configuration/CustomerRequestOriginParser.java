package com.example.demo.configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;

@Component
public class CustomerRequestOriginParser implements RequestOriginParser {

	@Override
	public String parseOrigin(HttpServletRequest request) {
		// 从额外的Header参数中取出来源应用（参数名_origin仅供参考）
		String origin = request.getHeader("_origin");
		if ("ba_qiang".equals(origin)) {
			// 标注来源为巴枪进行特殊处理：比如不限流
			return "ba_qiang";
		} else {
			return "non_baqiang";
		}
	}

}
