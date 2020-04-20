package com.zxtnet.singleItem.common.exceptionHandler;

import com.zxtnet.singleItem.common.responseResult.BaseResponse;
import com.zxtnet.singleItem.common.responseResult.ResponseCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 异常处理器
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param request       请求对象
     * @param exception     异常对象
     * @return              响应结果
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse allExceptionHandler(HttpServletRequest request, Exception exception) {
        return BaseResponse.getBaseResponse(ResponseCodeEnum.OPT_FAILURE);
    }

    /**
     * 自定义异常处理
     * @param request       请求对象
     * @param exception     异常对象
     * @return              响应结果
     */
    @ExceptionHandler(value = MsgException.class)
    public BaseResponse loginExceptionHandler(HttpServletRequest request, Exception exception) {
        MsgException msgException = (MsgException) exception;
        ResponseCodeEnum responseCode = msgException.getResponseCode();
        return BaseResponse.getBaseResponse(responseCode);
    }

}
