package com.zxtnet.newItem.common.responseResult;

/**
 * @description: 响应码枚举类（包含各个响应码及其对应提示信息）
 * @author: 郭海斌
 * @createDate: 2020/4/20
 * @version: 1.0
 */
public enum ResponseCodeEnum {
    //  成功
    OPT_SUCCESS(200, "操作成功！"),
    AUTHORIZATION_SUCCESS(200, "授权成功！"),

    //  失败
    OPT_FAILURE(444, "操作失败！"),
    DB_FAILURE(444, "数据库异常，请联系客服人员！！！"),

    //  需要跳转
    NO_AUTHORIZATION(440, "未授权，请授权后使用该功能！"),
    ;

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
