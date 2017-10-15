package com.river.util;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by river  2017/9/28
 * desc:
 */
public class MessageUtil {

    /**
     * 将xml转换map
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        SAXReader saxReader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document document = saxReader.read(ins);

        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for(Element element : elementList) {
            map.put(element.getName(),element.getText());
        }
         ins.close();
        return map;

    }

    /**
     * 对象转xml
     * @param t
     * @return
     */
    public static <T> String textMessageToXml(T t) {
        XStream xStream = new XStream();
        xStream.alias("xml",t.getClass());
        return xStream.toXML(t);
    }

}
