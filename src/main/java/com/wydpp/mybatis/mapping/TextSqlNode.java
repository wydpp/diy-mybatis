package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 16:59
 * @Description:
 */
public class TextSqlNode implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        //todo
    }

    public boolean isDynamic() {
        return this.sqlText.indexOf("${") > -1;
    }

}