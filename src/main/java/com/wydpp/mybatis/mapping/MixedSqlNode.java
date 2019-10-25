package com.wydpp.mybatis.mapping;

import java.util.List;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 17:08
 * @Description:
 */
public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodes;
    public MixedSqlNode(List<SqlNode> sqlNodes){
        this.sqlNodes = sqlNodes;
    }
    @Override
    public void apply(DynamicContext context) {
        for(SqlNode sqlNode:sqlNodes){
            sqlNode.apply(context);
        }
    }
}