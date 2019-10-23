package com.wydpp.mybatis.mapping;

import javax.sql.DataSource;

/**
 * @Auther: duanpp
 * @Date: 2019/10/23 16:35
 * @Description: 对应configuration.xml文件中的Environment配置
 */
public final class Environment {

    private final String id;

    private final DataSource dataSource;

    public Environment(String id,DataSource dataSource){
        this.id = id;
        this.dataSource = dataSource;
    }

    public String getId() {
        return id;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}