package com.zxtnet.template.pay.service;

/**
 * @module:
 * @description:
 * @author: 郭海斌
 * @createDate: 2020/5/14
 * @version: 1.0
 */
public interface PayService {

    /**
     * @description:    支付回调逻辑
     * @author 郭海斌
     * @date 2020/5/14
     */
    void payCallback() throws Exception;

}
