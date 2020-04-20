package com.zxtnet.singleItem.common.responseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 通用响应实体（不需要返回数据时使用该类）
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
@Data
@ApiModel(value = "响应实体类")
public class BaseResponse {
    @ApiModelProperty(value = "响应码：200为成功，444为失败，440为要跳转登录页")
    private int code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    protected BaseResponse(ResponseCodeEnum code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    //  获取响应实体
    public static BaseResponse getBaseResponse(ResponseCodeEnum code) {
        return new BaseResponse(code);
    }

}
