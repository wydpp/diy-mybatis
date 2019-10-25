package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 11:57
 * @Description: 封装查询字段和po映射关系
 */
public class ResultMapMapping {
    private boolean isId;
    private String column;
    private String jdbcType;
    private String property;

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("isId=").append(isId);
        sb.append(", column='").append(column).append('\'');
        sb.append(", jdbcType='").append(jdbcType).append('\'');
        sb.append(", property='").append(property).append('\'');
        sb.append('}');
        return sb.toString();
    }
}