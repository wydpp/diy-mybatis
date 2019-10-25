package com.wydpp.mybatis.mapping;

import java.util.List;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 14:13
 * @Description:
 */
public class ParameterMap {
    private String id;
    private Class parameterType;
    private List<ParameterMapMapping> parameterMapMappings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class parameterType) {
        this.parameterType = parameterType;
    }

    public List<ParameterMapMapping> getParameterMapMappings() {
        return parameterMapMappings;
    }

    public void setParameterMapMappings(List<ParameterMapMapping> parameterMapMappings) {
        this.parameterMapMappings = parameterMapMappings;
    }
}