package com.google;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class XMLdemo {
    @Test
    public void XMLReader() throws Exception {
        // 读取xml配置文件 获得document对象
        SAXReader saxReader = new SAXReader();
        // 通过类加载器获得指向字节码根路径下的指定文件输入流
        InputStream resourceAsStream = XMLdemo.class.getClassLoader().getResourceAsStream("db.xml");
        // 通过输入流获得配置文件 解析成一个dom对象
        Document read = saxReader.read(resourceAsStream);
        /**
         * Node节点：
         *      元素节点 element
         *      属性节点 attribute
         *      文本节点 text
         */
        Element rootElement = read.getRootElement();
        System.out.println(rootElement.getName());

        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            System.out.println("\t" + element.getName());
            Attribute id = element.attribute("id");
            System.out.println("\t\t" + "id:" + id.getText());
            List<Element> elements1 = element.elements();
            for (Element e : elements1) {
                System.out.println("\t\t" + e.getName() + ":" + e.getText());
            }
        }
    }
}
