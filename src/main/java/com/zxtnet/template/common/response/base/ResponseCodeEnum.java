package com.zxtnet.template.common.response.base;

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
    LOGIN_SUCCESS(200, "登录成功！"),

    //  失败
    OPT_FAILURE(444, "操作失败！"),
    DB_FAILURE(444, "数据库异常，请联系客服人员！！！"),
    SERVER_FAILURE(444, "服务器异常，正在修复服务器问题，请稍后使用！！！"),

    //  需要跳转
    NO_AUTHORIZATION(440, "用户未授权或授权超时，请重新授权！"),
    NO_LOGIN(440, "用户未登录或登录超时，请重新登录！"),
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
