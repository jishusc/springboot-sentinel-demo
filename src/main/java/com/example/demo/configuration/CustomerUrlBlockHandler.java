package com.example.demo.configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.config.WebServletConfig;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;

/**
 * 自定义限流处理逻辑，可以参考默认实现类：DefaultUrlBlockHandler 可以返回指定数据或者跳转到指定页面
 * 
 *
 */
@Component
public class CustomerUrlBlockHandler implements UrlBlockHandler {

	public static final String DEFAULT_BLOCK_MSG = "系统异常，请稍后再试";
	
	@Override
	public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex)
			throws IOException {
        StringBuffer url = request.getRequestURL();

        if ("GET".equals(request.getMethod()) && StringUtil.isNotBlank(request.getQueryString())) {
            url.append("?").append(request.getQueryString());
        }

        if (StringUtil.isBlank(WebServletConfig.getBlockPage())) {
            writeDefaultBlockedPage(response);
        } else {
            String redirectUrl = WebServletConfig.getBlockPage() + "?http_referer=" + url.toString();
            // Redirect to the customized block page.
            response.sendRedirect(redirectUrl);
        }
	}
	
    private static void writeDefaultBlockedPage(HttpServletResponse response) throws IOException {
    	//自定义返回 503 Service Unavailable
    	response.setStatus(503);
    	response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(DEFAULT_BLOCK_MSG);
        out.flush();
        out.close();
    }

	
}
