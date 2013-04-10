package com.jinmibao.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {

    /**
     * 查找rootElement下的elementName元素并获取元素的文本
     * 
     * @param elementName
     * @param rootElement
     * @return
     */
    public static String getElementText(String elementName, Element rootElement) {
        Element element = queryElement(elementName, 0, rootElement);
        return element != null ? element.getText() : null;
    }

    /**
     * 获取某元素下的第pos个元素名为elementName的元素
     * 
     * @param elementName 元素名
     * @param pos 位置，从0开始
     * @param element 源元素
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Element queryElement(String elementName, int pos, Element element) {
        Iterator<Element> it = element.elementIterator();
        Element myElement = null;
        int cp = -1;
        while (it.hasNext()) {
            myElement = it.next();
            if (elementName.equals(myElement.getName()) && ++cp == pos) {
                return myElement;
            } else {
                Element oElement = queryElement(elementName, pos, myElement);
                if (oElement != null) {
                    return oElement;
                }
            }
        }
        return null;
    }

    /**
     * 获取某元素element下面，元素为elementName，且这个元素含有属性attributeName和属性值为attrbutevalue的第一个元素
     * 
     * @param elementName 查找的元素名
     * @param attributeName 属性名
     * @param attrbuteValue 属性值
     * @param element 源元素
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Element queryElement(String elementName, String attributeName, String attrbuteValue, Element element) {
        Iterator<Element> it = element.elementIterator();
        Element myElement = null;
        while (it.hasNext()) {
            myElement = it.next();
            if (elementName.equals(myElement.getName()) && myElement.attributeValue(attributeName) != null
                && attrbuteValue.equals(myElement.attributeValue(attributeName))) {
                return myElement;
            } else {
                Element oElement = queryElement(elementName, attributeName, attrbuteValue, myElement);
                if (oElement != null) {
                    return oElement;
                }
            }
        }
        return null;
    }

    /**
     * 获取某元素element下面，元素为elementName，且这个元素含有属性attributeName的第一个元素
     * 
     * @param elementName 查找的元素名
     * @param attributeName 属性名
     * @param element 源元素
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Element queryElement(String elementName, String attributeName, Element element) {
        Iterator<Element> it = element.elementIterator();
        Element myElement = null;
        while (it.hasNext()) {
            myElement = it.next();
            if (elementName.equals(myElement.getName()) && myElement.attributeValue(attributeName) != null) {
                return myElement;
            } else {
                Element oElement = queryElement(elementName, attributeName, myElement);
                if (oElement != null) {
                    return oElement;
                }
            }
        }
        return null;
    }

    /**
     * 获取某元素element下面，元素为elementName，且这个元素含有属性attributeName的所有元素
     * 
     * @param elementName 查找的元素名
     * @param attributeName 属性名
     * @param element 源元素
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Element> queryElementsForList(String elementName, String attributeName, Element element) {
        Iterator<Element> it = element.elementIterator();
        List<Element> elementsList = new ArrayList<Element>();
        Element myElement = null;
        while (it.hasNext()) {
            myElement = it.next();
            if (elementName.equals(myElement.getName()) && myElement.attributeValue(attributeName) != null) {
                elementsList.add(myElement);
            } else {
                List<Element> oElementList = queryElementsForList(elementName, attributeName, myElement);
                if (oElementList != null && oElementList.size() > 0) {
                    elementsList.addAll(oElementList);
                }
            }
        }
        return elementsList;
    }

    /**
     * 读取XML文件，返回Document对象
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Document getDocumentByXMLFile(String fileName) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(fileName));
        return document;
    }

    /**
     * 读取XML文件，返回Document对象
     * 
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Document getDocumentByXMLFile(File file) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);
        return document;
    }

    public static Document getDocumentByInputStream(InputStream inputStream) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        return document;
    }

}
