package com.wydpp.mybatis.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 11:57
 * @Description: 封装查询字段和po映射关系
 */
public class ResultMap {
    private String resultMapId;
    private Class type;
    private Map<String,ResultMapMapping> results = new HashMap<>();

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Map<String, ResultMapMapping> getResults() {
        return results;
    }

    public void setResults(Map<String, ResultMapMapping> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultMap{");
        sb.append("resultMapId='").append(resultMapId).append('\'');
        sb.append(", type=").append(type);
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}