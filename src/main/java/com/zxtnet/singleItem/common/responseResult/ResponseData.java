package com.zxtnet.singleItem.common.responseResult;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 当需要向前台返回数据时使用该类
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 * @param <T>   响应数据的数据类型
 */
@Data
public class ResponseData<T> extends BaseResponse {
    @ApiModelProperty(value = "响应数据")
    private T data;

    private ResponseData(ResponseCodeEnum code, T data) {
        super(code);
        this.data = data;
    }

    //  获取响应实体
    public static <T> ResponseData<T> getResponseData(ResponseCodeEnum code, T data) {
        return new ResponseData<>(code, data);
    }
}
