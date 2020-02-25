package com.sx;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
    public <E> E selectOne(String statementId, Object...params) throws Exception;
    public <T> List<T> selectList(String statementId, Object...params) throws Exception;
    public void update(String statementId, Object...params) throws Exception;
    public void delete(String statementId, Object...params) throws Exception;
    public void insert(String statementId, Object...params) throws Exception;
    public <T> T getMapper(Class<T> clazz);
    public void close();
}
