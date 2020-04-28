package com.zxtnet.newItem.common.util;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description:    XML工具类
 * @author: 郭海斌
 * @createDate: 2020/4/23
 * @version: 1.0
 */
public class XMLUtil {

    /**
     * @description:    将xml解析为map，不包括根节点
     * @author 郭海斌
     * @date 2020/4/28
     * @param xmlStr    xml字符串
     * @return          根节点下的所有子节点的map格式值
     */
    public static Map xml2Map(String xmlStr) {
        if(null == xmlStr || "".equals(xmlStr)) {
            return null;
        }
        //  转换编码
        xmlStr = xmlStr.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        Map resultMap = new HashMap();
        //  指定编码格式(UTF-8)转换为字节流
        try(InputStream is = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));) {
            //  开始解析
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(is);
            //  根节点
            Element root = doc.getRootElement();
            //  子节点
            List<Element> rootChildren = root.getChildren();
            //  将子节点转换为map
            resultMap = children2Map(rootChildren);
        } catch (Exception e) {
            System.err.println("*************解析xml失败！");
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * @description:    将所有子节点组合为map
     * @author 郭海斌
     * @date 2020/4/28
     * @param children  子节点
     * @return
     */
    private static Map children2Map(List<Element> children) {
        Iterator<Element> iterator = children.iterator();
        Map map = new HashMap();
        //  遍历后代
        while (iterator.hasNext()) {
            //  获取后代
            Element next = iterator.next();
            //  标签名
            String key = next.getName();
            //  值
            Object val;
            //  后代的后代
            List<Element> nextChildren = next.getChildren();
            //  后代没有
            if (nextChildren.isEmpty()) {
                val = next.getTextNormalize();
            } else {
                val = children2Map(nextChildren);
            }
            map.put(key, val);
        }
        return map;
    }

}
