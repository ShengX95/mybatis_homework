package com.sx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author shengx
 * @date 2020/2/24 21:12
 */
public class MapperProxy<T> implements InvocationHandler {
    SqlSession sqlSession;
    Configuration configuration;

    public MapperProxy(SqlSession sqlSession, Configuration configuration) {
        this.sqlSession = sqlSession;
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz = method.getDeclaringClass();
        String methodName = method.getName();
        String statementid = clazz.getName() + "." + methodName;
        MappedStatement mappedStatement = configuration.getMap().get(statementid);
        if(mappedStatement.isSelect()){
            if(method.getGenericReturnType() instanceof ParameterizedType){
                return sqlSession.selectList(statementid, args);
            }
            return sqlSession.selectOne(statementid, args);
        }else {
            sqlSession.update(statementid, args);
        }
        return null;
    }
}
