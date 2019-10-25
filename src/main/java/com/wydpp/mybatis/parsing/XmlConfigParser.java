package com.wydpp.mybatis.parsing;

import com.wydpp.mybatis.datasource.unpooled.UnpooledDataSource;
import com.wydpp.mybatis.mapping.*;
import com.wydpp.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:41
 * @Description:
 */
public class XmlConfigParser {

    public Configuration parseConfiguration(Element rootElement){
        //解析数据源
        Environment environment = parseEnvironments(rootElement.element("environments"));
        Configuration configuration = new Configuration();
        configuration.setEnvironment(environment);
        //解析Mapper.xml文件
        Map<String, MappedStatement> mappedStatements = parseMapper(rootElement);
        configuration.setMappedStatements(mappedStatements);
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

    /**
     * 解析XXXMapper.xml文件
     * @param rootElement
     * @return
     */
    private Map<String, MappedStatement> parseMapper(Element rootElement) {
        Element mapperElements = rootElement.element("mappers");
        if(mapperElements == null){
            return null;
        }
        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        for(Object element:mapperElements.elements("mapper")){
            Map<String,MappedStatement> statements = parseMappedStatement((Element)element);
            mappedStatements.putAll(statements);
        }
        return mappedStatements;
    }

    /**
     * 解析一个xxxMapper.xml文件，可能会有多个MappedStatement
     * @param element
     * @return
     */
    private Map<String,MappedStatement> parseMappedStatement(Element element) {
        Map<String, MappedStatement> mappedStatements = new HashMap<>();
        String resource = element.attributeValue("resource");
        InputStream inputStream = XmlConfigParser.class.getClassLoader().getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);
        if(document == null){
            //todo exception
            return mappedStatements;
        }
        Element rootMapperElement = document.getRootElement();
        //todo resultMap如何放入Configuration对象中呢？需要放入吗？
        //解析resultMap映射内容
        Map<String, ResultMap> resultMapMap = parseResultMap(rootMapperElement);
        //解析statement
        String namespace = rootMapperElement.attributeValue("namespace");
        List<Element> selectElements = rootMapperElement.elements("select");
        for(Element selectEle:selectElements){
            MappedStatement mappedStatement = parseSelectElement(selectEle,namespace,resultMapMap);
            mappedStatements.put(mappedStatement.getStatementId(),mappedStatement);
        }
        //todo update delete
        return mappedStatements;
    }

    private Map<String, ResultMap> parseResultMap(Element rootMapperElement){
        //todo
        return new HashMap<>();
    }

    private MappedStatement parseSelectElement(Element selectElement,String namespace, Map<String, ResultMap> resultMapMap){
        MappedStatement mappedStatement = new MappedStatement();
        String id = selectElement.attributeValue("id");
        String parameterType = selectElement.attributeValue("parameterType");
        Class parameterTypeClass = resolveClass(parameterType);
        String resultType = selectElement.attributeValue("resultType");
        Class resultTypeClass = resolveClass(resultType);
        String resultMap = selectElement.attributeValue("resultMap");
        if(resultMap != null){
            mappedStatement.setResultMap(resultMapMap.get(resultMap));
        }
        mappedStatement.setStatementId(namespace+id);
        mappedStatement.setResultType(resultType);
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.setParameterType(parameterTypeClass);
        parameterMap.setId(parameterType);
        mappedStatement.setParameterMap(parameterMap);
        mappedStatement.setResultTypeClass(resultTypeClass);

        //解析sql内容
        XmlScriptParser xmlScriptParser = new XmlScriptParser();
        SqlSource sqlSource = xmlScriptParser.parseScriptNode(selectElement);
        mappedStatement.setSqlSource(sqlSource);
        return mappedStatement;
    }

    private Class<?> resolveClass(String parameterType) {
        if(parameterType == null){
            return null;
        }
        try {
            return Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}