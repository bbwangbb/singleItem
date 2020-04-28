package com.zxtnet.newItem.common.interceptor;

import com.zxtnet.newItem.common.exceptionHandler.MsgException;
import com.zxtnet.newItem.common.responseResult.ResponseCodeEnum;
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
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //  获取token
//        String token = request.getHeader("token");
//        //  token不存在无法进入某些操作
//        if (token == null) {
//            log.info("**************用户未授权，无法进行此操作！");
//            throw new MsgException(ResponseCodeEnum.NO_AUTHORIZATION);
//        }
//        //  目前不存在token超时
//        log.info("******************用户已授权，开始进行此操作！");
        return true;
    }
}
