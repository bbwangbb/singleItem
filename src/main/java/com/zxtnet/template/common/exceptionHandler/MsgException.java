package com.zxtnet.template.common.exceptionHandler;

import com.zxtnet.template.common.response.base.ResponseCodeEnum;
import lombok.Data;

/**
 * @description: 自定义异常：提示信息异常
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Data
public class MsgException extends RuntimeException {

    private ResponseCodeEnum responseCode;

    public MsgException() {
        //  无参默认为操作失败
        this(ResponseCodeEnum.OPT_FAILURE);
    }

    public MsgException(String message) {
        super(message);
    }

    public MsgException(ResponseCodeEnum responseCode) {
        this.responseCode = responseCode;
    }

}