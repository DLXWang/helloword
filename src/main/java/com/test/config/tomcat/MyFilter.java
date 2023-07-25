/*
package com.test.config.tomcat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，在过滤器被加载时执行（可选）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 过滤器的逻辑，处理请求前的操作
        System.out.println("MyFilter is doing pre-processing...");

        // 继续处理请求链，将请求传递给下一个过滤器或目标资源
        chain.doFilter(request, response);

        // 过滤器的逻辑，处理响应后的操作
        System.out.println("MyFilter is doing post-processing...");
    }

    @Override
    public void destroy() {
        // 销毁方法，在过滤器被卸载时执行（可选）
    }
}

*/
