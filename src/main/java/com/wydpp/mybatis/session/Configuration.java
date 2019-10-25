package com.wydpp.mybatis.session;

import com.wydpp.mybatis.mapping.Environment;
import com.wydpp.mybatis.mapping.MappedStatement;
import com.wydpp.mybatis.mapping.ResultMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:33
 * @Description:
 */
public class Configuration {
    /**
     * 里面包含数据源dataSource
     */
    private Environment environment;
    /**
     * 一个完整的crud标签（如：<select>...</select>），对应一个MappedStatement。相应的每个MappedStatment中包含了sql语句，入参信息，返回类型
     */
    private Map<String, MappedStatement> mappedStatements;
    /**
     * resultMap集合
     */
    protected final Map<String, ResultMap> resultMaps = new HashMap<>();

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }
}