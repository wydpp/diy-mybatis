package com.wydpp.mybatis.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: duanpp
 * @Date: 2019/10/24 13:22
 * @Description: 封装已经完全解析成功的sql语句和解析出来的参数信息集合
 */
public class BoundSql {

    private String sql;

    private List<ParameterMapMapping> parameterMapMappingList = new ArrayList<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapMapping> getParameterMapMappingList() {
        return parameterMapMappingList;
    }

    public void addParameterMapMapping(ParameterMapMapping parameterMapMapping) {
        this.parameterMapMappingList.add(parameterMapMapping);
    }
}