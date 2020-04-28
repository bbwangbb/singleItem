package com.zxtnet.newItem.common.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description:    微信小程序相关配置
 * @author: 郭海斌
 * @createDate: 2020/4/24
 * @version: 1.0
 */
public class WeChatParamConfig {

    //  小程序app_id
    public static final String APP_ID = "";

    //  小程序密钥
    public static final String APP_SECRET = "";

    //  微信商户号
    public static final String MCH_ID = "";

    //  微信商户号密钥
    public static final String API_KEY = "";

    //  授权url
    public static final String AUTHORISE_URL = "https://api.weixin.qq.com/sns/jscode2session";

    //  支付url
    public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //  todo：支付回调url，需要修改
    public static final String PAY_CALLBACK_URL = "http://t.zxtnet.com:8082/pay/wxPayForUnlockCallback";

}
