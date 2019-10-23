package com.wydpp.mybatis.parsing;

import com.wydpp.mybatis.mapping.Environment;
import com.wydpp.mybatis.session.Configuration;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:45
 * @Description:
 */
public class XmlConfigParserTest {

    @Test
    public void testParseConfiguration(){
        XmlConfigParser parser = new XmlConfigParser();
        InputStream inputStream = XmlConfigParserTest.class.getClassLoader().getResourceAsStream("configuration.xml");
        Document document = DocumentUtils.readDocument(inputStream);
        Configuration configuration = parser.parseConfiguration(document.getRootElement());
        assertNotNull(configuration);
        assertNotNull(configuration.getEnvironment());
        Environment environment = configuration.getEnvironment();
        assertEquals(environment.getId(),"development");
    }
}