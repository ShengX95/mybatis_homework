package com.sx;

/**
 * @author shengx
 * @date 2020/2/22 19:09
 */
public class MappedStatement {
    private String id;
    private String sql;
    private Class<?> paramterType;
    private Class<?> resultType;
    private boolean isSelect;

    public String getId() {
        return id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<?> getParamterType() {
        return paramterType;
    }

    public void setParamterType(Class<?> paramterType) {
        this.paramterType = paramterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }
}
