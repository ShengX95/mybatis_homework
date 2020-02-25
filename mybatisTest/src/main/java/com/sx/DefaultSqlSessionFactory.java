package com.sx;

/**
 * @author shengx
 * @date 2020/2/22 19:07
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
