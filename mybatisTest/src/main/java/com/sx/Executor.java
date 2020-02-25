package com.sx;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Executor {
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object...params) throws Exception;
    void update(Configuration configuration, MappedStatement mappedStatement, Object...params) throws Exception;
    void close();
}
