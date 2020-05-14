package com.zxtnet.template.common.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zxtnet.template.common.config.Constant;
import com.zxtnet.template.common.exceptionHandler.MsgException;
import com.zxtnet.template.common.response.base.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @description:    微信工具类
 * @author: 郭海斌
 * @createDate: 2020/4/23
 * @version: 1.0
 */
@Component
@Slf4j
public class WeChatUtil {

    /**
     * @description:    获取微信用户的openid
     * @author 郭海斌
     * @date 2020/4/23
     * @param code      小程序授权传的参数
     * @return          获取信息
     */
    public static JSONObject getWxUserOpenid(String code, String appId, String appSecret) {
        //拼接url
        StringBuilder url = new StringBuilder(Constant.WeChat.AUTHORISE_URL);
        url.append("appid=");//appid设置
        url.append(appId);
        url.append("&secret=");//secret设置
        url.append(appSecret);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");
        JSONObject result = new JSONObject();
        try {
            //  发送请求获取响应值
            String response = HttpRequest.get(url.toString()).execute().body();
            //  将响应值转为Map
            result = JSONUtil.parseObj(response);
            //  解析成功会包含openid
            if (result == null || result.size() == 0 || !result.containsKey("openid")) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description:    微信支付
     * @author 郭海斌
     * @date 2020/4/24
     * @param openId    用户openid
     * @param totalFee  交易金额
     * @param orderNo   交易号/订单号
     * @param body      订单信息
     * @return          小程序调起支付页面的参数
     */
    public static JSONObject pay(String openId, int totalFee, String orderNo, String body, String appId, String mchId, String apiKey) {
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //  微信小程序ID
        packageParams.put("appid", appId);
        //  商户ID
        packageParams.put("mch_id", mchId);
        //  JSAPI必须要openid
        packageParams.put("openid", openId);
        //  随机字符串（32位以内）
        packageParams.put("nonce_str", RandomUtil.randomString(32));
        //  支付主体名称 自定义
        packageParams.put("body", body);
        //  订单编号
        packageParams.put("out_trade_no", orderNo);
        //  价格：1就是1分
        packageParams.put("total_fee", totalFee + "");
        //  支付返回地址要外网访问的到， localhost不行，调用下面buy方法。（订单存入数据库）
        packageParams.put("notify_url", Constant.WeChat.PAY_CALLBACK_URL);
        //  JSAPI：微信小程序支付时使用这个
        packageParams.put("trade_type", "JSAPI");
        //  获取sign - 这个是自己在微信商户设置的32位密钥
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, apiKey);
        packageParams.put("sign", sign);
        //  将参数转成XML
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        //  得到含有prepay_id的XML
        String resXml = HttpRequest.post(Constant.WeChat.PAY_URL).body(requestXML).execute().body();
        //  解析XML存入Map
        Map map = XMLUtil.xml2Map(resXml);
        //  判断请求是否执行成功
        isRequestSuccess(map);
        //  得到prepay_id
        String prepay_id = (String) map.get("prepay_id");
        SortedMap<Object, Object> packageP = new TreeMap<>();
        //  别忘了这个，也需要放进去
        packageP.put("appId", appId);
        //  随机字符串（32位以内）
        packageP.put("nonceStr", RandomUtil.randomString(32));
        //  必须把package写成 "prepay_id="+prepay_id这种形式
        packageP.put("package", "prepay_id=" + prepay_id);
        //  paySign加密
        packageP.put("signType", "MD5");
        //  时间戳（这里会除1000，若需计算超时注意乘1000）
        packageP.put("timeStamp", String.valueOf((System.currentTimeMillis() / 1000)));
        //  得到paySign
        String paySign = PayCommonUtil.createSign("UTF-8", packageP, apiKey);
        packageP.put("paySign", paySign);
        return new JSONObject(packageP);
    }


    /**
     * @description:    判断业务是否成功
     * @author 郭海斌
     * @date 2020/5/14
     * @param map       响应结果
     * @return          是否成功
     */
    private static boolean isBizSuccess(Map map) {
        if ("FAIL".equals(map.get("result_code"))) {
            log.info("***********************业务处理失败，具体原因如下：" +
                    "********************************" + map.get("err_code_des"));
            throw new MsgException(ResponseCodeEnum.SERVER_FAILURE);
        }
        log.info("***********************业务成功！");
        return true;
    }

    /**
     * @description:    判断请求是否成功
     * @author 郭海斌
     * @date 2020/5/14
     * @param map       响应结果
     * @return          是否成功
     */
    public static void isRequestSuccess(Map map) {
        if ("FAIL".equals(map.get("return_code"))) {
            log.info("***********************请求失败，具体原因如下：" +
                    "********************************" + map.get("return_msg"));
            throw new MsgException(ResponseCodeEnum.SERVER_FAILURE);
        }
        log.info("***********************请求成功！");
    }

    /**
     * @description:查询订单
     *      如果订单存在会返回信息
     *      不存在result_code = FAIL & err_code = ORDERNOTEXIST
     * @author 郭海斌
     * @date 2020/5/14
     * @param orderNo   订单号
     * @return
     */
    public static JSONObject queryOrder(String orderNo, String appId, String mchId, String apiKey) {
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //  微信小程序ID
        packageParams.put("appid", appId);
        //  商户ID
        packageParams.put("mch_id", mchId);
        //  随机字符串（32位以内）
        packageParams.put("nonce_str", RandomUtil.randomString(32));
        //  订单编号
        packageParams.put("out_trade_no", orderNo);
        //  获取sign - 这个是自己在微信商户设置的32位密钥
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, apiKey);
        packageParams.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpRequest.post(Constant.WeChat.QUERY_ORDER_URL).body(requestXML).execute().body();
        System.err.println(resXml);
        //  解析XML存入Map
        Map map = map = XMLUtil.xml2Map(resXml);
        isRequestSuccess(map);
        return new JSONObject(map);
    }

}
