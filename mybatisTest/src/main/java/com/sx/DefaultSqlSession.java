package com.sx;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shengx
 * @date 2020/2/22 20:34
 */
public class DefaultSqlSession implements SqlSession {
    Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E selectOne(String statementId, Object... params) throws Exception {
        List<E> results = selectList(statementId, params);
        if(results !=null && results.size()>0){
            return results.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMap().get(statementId);
        return executor.query(configuration, mappedStatement, params);
    }

    @Override
    public void update(String statementId, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMap().get(statementId);
        executor.update(configuration, mappedStatement, params);
    }

    @Override
    public void delete(String statementId, Object... params) throws Exception {
        update(statementId, params);
    }

    @Override
    public void insert(String statementId, Object... params) throws Exception {
        update(statementId, params);
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this, configuration));
    }

    @Override
    public void close() {

    }
}
