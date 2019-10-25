package com.wydpp.mybatis.mapping;

/**
 * @Auther: duanpp
 * @Date: 2019/10/24 11:33
 * @Description: 封装crud标签
 */
public final class MappedStatement {
    private String statementId;
    private Class parameterTypeClass;
    private Class resultTypeClass;
    private SqlSource sqlSource;

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public Class getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public Class getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MappedStatement{");
        sb.append("statementId='").append(statementId).append('\'');
        sb.append(", parameterTypeClass=").append(parameterTypeClass);
        sb.append(", resultTypeClass=").append(resultTypeClass);
        sb.append(", sqlSource=").append(sqlSource);
        sb.append('}');
        return sb.toString();
    }
}