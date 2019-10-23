package com.wydpp.mybatis.parsing;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:47
 * @Description:
 */
public class DocumentUtils {
    public static Document readDocument(InputStream inputStream) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}