package com.zxtnet.template.common.config;

/**
 * @module: 配置模块
 * @description: 常量类 todo：需要配置的常量
 * @author: 郭海斌
 * @createDate: 2020/5/9
 * @version: 1.0
 */
public class Constant {

    /**
     * @description:    Redis相关配置
     * @author 郭海斌
     * @date 2020/5/9
     * @version: 1.0
     */
    public class Redis {
        //  超时时间(min)
        public static final int EXPIRE_TIME_MINUTE = 30;
    }

    /**
     * @description:    微信小程序相关配置
     * @author: 郭海斌
     * @createDate: 2020/4/24
     * @version: 1.0
     */
    public class WeChat {
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

        //  查询订单状态url
        public static final String QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

        //  todo：支付回调url，需要修改
        public static final String PAY_CALLBACK_URL = "http://t.zxtnet.com/api/pay/payCallback";
    }

}
