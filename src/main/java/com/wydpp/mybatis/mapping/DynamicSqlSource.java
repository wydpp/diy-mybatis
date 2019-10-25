package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 17:11
 * @Description:
 */
public class DynamicSqlSource implements SqlSource {
    private SqlNode sqlNode;
    public DynamicSqlSource(MixedSqlNode rootSqlNode){
        this.sqlNode = rootSqlNode;
    }
    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}