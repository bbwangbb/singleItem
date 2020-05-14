package com.zxtnet.template.pay.service.impl;

import com.zxtnet.template.common.util.PayCommonUtil;
import com.zxtnet.template.common.util.PayUtil;
import com.zxtnet.template.common.util.XMLUtil;
import com.zxtnet.template.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    /**
     * @param resultMap 支付响应
     * @description: 支付后的个人回调逻辑
     * @author 郭海斌
     * @date 2020/5/14
     */
    private void weOwnLogic(Map resultMap) {
        //  todo：写支付成功后逻辑
    }

    @Override
    public void payCallback() throws Exception {
        log.info("****************************执行支付回调函数。。。");
        //  读取返回值
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //  sb为微信返回的xml
        String returnXml = sb.toString();
        String resXml;
        Map resultMap = XMLUtil.xml2Map(returnXml);
        log.info("****************************将xml报文解析后的map：");
        resultMap.forEach((x, y) -> {
            log.info("*******" + x + " --- " + y);
        });
        String returnCode = (String) resultMap.get("return_code");
        System.err.println("return_code：" + returnCode);
        if ("SUCCESS".equals(returnCode)) {
            log.info("****************************验证签名是否正确");
            //  验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(resultMap);  //回调验签时需要去除sign和空值参数
            String validStr = PayCommonUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //  todo：获取小程序对应商户号密钥
            String apiKey = "";
            String sign = PayUtil.sign(validStr, apiKey, "utf-8").toUpperCase();//拼装生成服务器端验证的签名
            //  因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了
            //  根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (sign.equals(resultMap.get("sign"))) {
                log.info("****************************签名正确，开始执行业务逻辑");
                weOwnLogic(resultMap);
                log.info("****************************业务逻辑执行结束，通知微信服务器支付成功");
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                log.info("****************************微信支付回调失败！签名不一致！");
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[签名不一致]]></return_msg>" + "</xml> ";
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println("响应报文：" + resXml);
        log.info("****************************微信支付回调数据结束！");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


}
