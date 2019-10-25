package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 17:13
 * @Description:
 */
public class RawSqlSource implements SqlSource {
    private SqlNode rootSqlNode;

    public RawSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
        //todo 在这里要先对sql节点进行解析
    }
    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}