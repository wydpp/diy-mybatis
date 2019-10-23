package com.wydpp.mybatis.session;

import com.wydpp.mybatis.mapping.Environment;

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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}