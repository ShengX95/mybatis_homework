package com.sx;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shengx
 * @date 2020/2/22 19:06
 */
public class Configuration {
    DataSource dataSource;
    Map<String, MappedStatement> map = new HashMap<>();

    public Configuration() {
        this.map = new HashMap<>();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMap() {
        return map;
    }

    public void setMap(Map<String, MappedStatement> map) {
        this.map = map;
    }
}
