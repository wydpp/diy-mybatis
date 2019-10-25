package com.wydpp.mybatis.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: duanpp
 * @Date: 2019/10/25 16:57
 * @Description: 存储SqlNode解析过程中产生的sql片段，并完成字符串拼接 存储SqlNode解析过程中需要的入参信息
 */
public class DynamicContext {

    private StringBuilder sb = new StringBuilder();

    private Map<String,Object> bindings = new HashMap<>();

    public void appendSql(String sql){
        sb.append(sql).append(" ");
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, Object> bindings) {
        this.bindings = bindings;
    }
}