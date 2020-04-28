package com.zxtnet.newItem.test;

import cn.hutool.core.util.IdUtil;
import com.zxtnet.newItem.common.util.WeChatUtil;

import java.util.Map;

/**
 * @module:
 * @description:
 * @author: 郭海斌
 * @createDate: 2020/4/28
 * @version: 1.0
 */
public class TestWechatUtil {
    public static void main(String[] args) {
        WeChatUtil weChatUtil = new WeChatUtil();
        Map pay = weChatUtil.pay("oRP1r5dxpwYIHcIXVD2TJDm9qkkw",
                1,
                IdUtil.simpleUUID(),
                "bbj");
        System.out.println(pay);


    }
}
