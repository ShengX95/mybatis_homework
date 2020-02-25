package com.sx;

import java.util.List;

/**
 * @author shengx
 * @date 2020/2/22 20:42
 */
public class BoundSql {
    private String sql;
    private List<ParameterMapping> paramterMappings;

    public BoundSql(String sql, List<ParameterMapping> paramterMappings) {
        this.sql = sql;
        this.paramterMappings = paramterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParamterMappings() {
        return paramterMappings;
    }

    public void setParamterMappings(List<ParameterMapping> paramterMappings) {
        this.paramterMappings = paramterMappings;
    }

    @Override
    public String toString() {
        return "BoundSql{" +
                "sql='" + sql + '\'' +
                ", paramterMappings=" + paramterMappings +
                '}';
    }
}
