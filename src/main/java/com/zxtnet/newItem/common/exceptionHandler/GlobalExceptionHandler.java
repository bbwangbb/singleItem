package com.zxtnet.newItem.common.exceptionHandler;

import com.zxtnet.newItem.common.responseResult.BaseResponse;
import com.zxtnet.newItem.common.responseResult.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param request       请求对象
     * @param exception     异常对象
     * @return              响应结果
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse allExceptionHandler(HttpServletRequest request, Exception exception) {
        return BaseResponse.getBaseResponse(444, exception.getMessage());
    }

    /**
     * 自定义异常处理
     * @param request       请求对象
     * @param exception     异常对象
     * @return              响应结果
     */
    @ExceptionHandler(value = MsgException.class)
    public BaseResponse customExceptionHandler(HttpServletRequest request, Exception exception) {
        MsgException msgException = (MsgException) exception;
        log.info("***************自定义异常，信息如下：\n" + exception.getMessage());
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
        log.info("***************数据库异常，信息如下：\n" + exception.getMessage());
        return BaseResponse.getBaseResponse(ResponseCodeEnum.DB_FAILURE);
    }

}
