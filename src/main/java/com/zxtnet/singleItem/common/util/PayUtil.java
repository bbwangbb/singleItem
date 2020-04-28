package com.zxtnet.singleItem.common.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PayUtil {


    /**
     *      * 签名字符串
     *      * @param text 需要签名的字符串
     *      * @param key 密钥
     *      * @param input_charset 编码格式
     *      * @return 签名结果
     *      
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + "&key=" + key;
        return DigestUtil.md5Hex(getContentBytes(text, input_charset));
    }


    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


    /**
     *      * 除去数组中的空值和签名参数
     *      * @param sArray 签名参数组
     *      * @return 去掉空值与签名参数后的新签名参数组
     *      
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
}
