package com.zxtnet.newItem.common.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zxtnet.newItem.common.config.WeChatParamConfig;
import com.zxtnet.newItem.common.exceptionHandler.MsgException;
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
    public Map<String, Object> getWxUserOpenid(String code) {
        //拼接url
        StringBuilder url = new StringBuilder(WeChatParamConfig.AUTHORISE_URL);
        url.append("?appid=");//appid设置
        url.append(WeChatParamConfig.APP_ID);
        url.append("&secret=");//secret设置
        url.append(WeChatParamConfig.APP_SECRET);
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
    public Map pay(String openId, int totalFee, String orderNo, String body) {
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        //  微信小程序ID
        packageParams.put("appid", WeChatParamConfig.APP_ID);
        //  商户ID
        packageParams.put("mch_id", WeChatParamConfig.MCH_ID);
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
        packageParams.put("notify_url", WeChatParamConfig.PAY_CALLBACK_URL);
        //  JSAPI：微信小程序支付时使用这个
        packageParams.put("trade_type", "JSAPI");
        //  获取sign - 这个是自己在微信商户设置的32位密钥
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, WeChatParamConfig.API_KEY);
        packageParams.put("sign", sign);
        //  将参数转成XML
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        //  得到含有prepay_id的XML
        String resXml = HttpRequest.post(WeChatParamConfig.PAY_URL).body(requestXML).execute().body();
        //  解析XML存入Map
        Map map = XMLUtil.xml2Map(resXml);
        //  如果执行失败，直接报异常
        if ("FAIL".equals(map.get("return_code"))) {
            log.info("***********************支付获取参数失败，具体原因如下：");
            log.info("********************************" + map.get("return_msg"));
            throw new MsgException("服务器问题，支付获取参数失败！");
        }
        //  得到prepay_id
        String prepay_id = (String) map.get("prepay_id");
        SortedMap<Object, Object> packageP = new TreeMap<>();
        //  别忘了这个，也需要放进去
        packageP.put("appId", WeChatParamConfig.APP_ID);
        //  随机字符串（32位以内）
        packageP.put("nonceStr", RandomUtil.randomString(32));
        //  必须把package写成 "prepay_id="+prepay_id这种形式
        packageP.put("package", "prepay_id=" + prepay_id);
        //  paySign加密
        packageP.put("signType", "MD5");
        //  时间戳
        packageP.put("timeStamp", String.valueOf((System.currentTimeMillis() / 1000)));
        //  得到paySign
        String paySign = PayCommonUtil.createSign("UTF-8", packageP, WeChatParamConfig.API_KEY);
        packageP.put("paySign", paySign);
        return packageP;
    }

}
