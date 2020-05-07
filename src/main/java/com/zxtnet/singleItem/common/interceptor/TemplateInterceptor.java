package com.zxtnet.singleItem.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @module: 拦截器
 * @description:    授权拦截器模块
 * @author: 郭海斌
 * @createDate: 2020/4/23
 * @version: 1.0
 */
@Slf4j
public class TemplateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("****************************当前请求uri为：" + request.getRequestURI());
        //  请求进入前
        return false;
    }
}
