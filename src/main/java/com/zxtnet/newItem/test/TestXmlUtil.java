package com.zxtnet.newItem.test;

import cn.hutool.core.util.XmlUtil;
import com.zxtnet.newItem.common.util.XMLUtil;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @module:
 * @description:
 * @author: 郭海斌
 * @createDate: 2020/4/28
 * @version: 1.0
 */
public class TestXmlUtil {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\ideaProjects\\newItem\\src\\main\\java\\com\\zxtnet\\newItem\\test\\test.xml";
        InputStream is = new FileInputStream(new File(filePath));
        Document document = XmlUtil.readXML(new File(filePath));
        System.out.println(document);


//        Map map = XMLUtil.xml2Map("<user>" +
//                "<name>" +
//                    "<![CDATA[bb]]>" +
//                    "<sex>man</sex>" +
//                    "<a>a</a>" +
//                "</name></user>");
        Map map = XMLUtil.xml2Map("<xml>\n" +
                "    <appid><![CDATA[wxba432accd086da6b]]></appid>\n" +
                "    <bank_type><![CDATA[OTHERS]]></bank_type>\n" +
                "    <cash_fee><![CDATA[1]]></cash_fee>\n" +
                "    <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "    <is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "    <mch_id><![CDATA[1241421402]]></mch_id>\n" +
                "    <nonce_str><![CDATA[zfdkcjmgq7drlkt43v74hocc8lj455e2]]></nonce_str>\n" +
                "    <openid><![CDATA[oRP1r5dxpwYIHcIXVD2TJDm9qkkw]]></openid>\n" +
                "    <out_trade_no><![CDATA[049f474323414894a32d754189058695]]></out_trade_no>\n" +
                "    <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "    <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "    <sign><![CDATA[5C2CA0AD78D1C524AAF13EF7187C6436]]></sign>\n" +
                "    <time_end><![CDATA[20200426162317]]></time_end>\n" +
                "    <total_fee>1</total_fee>\n" +
                "    <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "    <transaction_id><![CDATA[4200000535202004260246407309]]></transaction_id>\n" +
                "</xml>");
        System.out.println(map);
    }
}
