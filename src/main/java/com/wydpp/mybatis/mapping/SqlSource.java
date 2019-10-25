package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/24 13:21
 * @Description:
 */
public interface SqlSource {

    BoundSql getBoundSql(Object param);
}