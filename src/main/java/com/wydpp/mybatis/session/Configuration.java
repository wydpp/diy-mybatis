package com.wydpp.mybatis.session;

import com.wydpp.mybatis.mapping.Environment;
import com.wydpp.mybatis.mapping.MappedStatement;

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

    private Map<String, MappedStatement> mappedStatements;

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