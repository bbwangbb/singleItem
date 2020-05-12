package com.zxtnet.template.common.exceptionHandler;

import com.zxtnet.template.common.response.base.BaseResponse;
import com.zxtnet.template.common.response.base.ResponseCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

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
        return BaseResponse.getBaseResponse(ResponseCodeEnum.SERVER_FAILURE);
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
        //  如果设置了响应码对象就按照响应码对象来，否则返回提示信息
        return responseCode == null ? BaseResponse.getBaseResponse(444, exception.getMessage()) :
                BaseResponse.getBaseResponse(responseCode);
    }

    /**
     * 数据库异常处理
     * @param request       请求对象
     * @param exception     异常对象
     * @return              响应结果
     */
    @ExceptionHandler(value = SQLException.class)
    public BaseResponse sqlExceptionHandler(HttpServletRequest request, Exception exception) {
        return BaseResponse.getBaseResponse(ResponseCodeEnum.DB_FAILURE);
    }

}
