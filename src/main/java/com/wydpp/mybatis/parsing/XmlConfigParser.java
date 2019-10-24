package com.wydpp.mybatis.parsing;

import com.wydpp.mybatis.datasource.unpooled.UnpooledDataSource;
import com.wydpp.mybatis.mapping.Environment;
import com.wydpp.mybatis.session.Configuration;
import org.dom4j.Element;

import java.util.List;
import java.util.Properties;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:41
 * @Description:
 */
public class XmlConfigParser {

    public Configuration parseConfiguration(Element rootElement){
        Environment environment = parseEnvironments(rootElement.element("environments"));
        Configuration configuration = new Configuration();
        configuration.setEnvironment(environment);
        return configuration;
    }

    private Environment parseEnvironments(Element environments) {
        if(environments == null){
            return null;
        }
        String defaultId = environments.attributeValue("default");
        if(defaultId == null || defaultId.trim().isEmpty()){
            return null;
        }
        List<Element> elements = environments.elements();
        if(elements == null){
            return null;
        }
        Environment environment = null;
        for(Element ele:elements){
            String id = ele.attributeValue("id");
            if(defaultId.equalsIgnoreCase(id)){
                environment = parseEnvironment(id,ele);
                break;
            }
        }
        return environment;
    }

    private Environment parseEnvironment(String id,Element ele) {
        Element dataSourceEle = ele.element("dataSource");
        if(dataSourceEle == null){
            return null;
        }
        //默认使用自定义的unpooled链接池
        String type = dataSourceEle.attributeValue("type");
        if(type == null){
            type = "unpooled";
        }
        Environment environment = null;
        if(type.equalsIgnoreCase("unpooled")){
            Properties properties = parseProperty(dataSourceEle);
            UnpooledDataSource dataSource = new UnpooledDataSource();
            dataSource.setDriver(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            environment = new Environment(id,dataSource);
        }else{
            //todo 其他数据源省略
        }
        return environment;
    }

    private Properties parseProperty(Element dataSource){
        Properties properties = new Properties();
        List<Element> elements = dataSource.elements("property");
        for(Element ele:elements){
            properties.put(ele.attributeValue("name"),ele.attributeValue("value"));
        }
        return properties;
    }
}