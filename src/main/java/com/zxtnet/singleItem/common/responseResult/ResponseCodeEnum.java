package com.zxtnet.singleItem.common.responseResult;

/**
 * @description: 响应码枚举类（包含各个响应码及其对应提示信息）
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
public enum ResponseCodeEnum {
    //  成功
    OPT_SUCCESS(200, "操作成功！"),

    //  失败
    OPT_FAILURE(444, "操作失败！");

    private int code;

    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
