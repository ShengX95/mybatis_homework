package com.sx;

import com.mysql.jdbc.JDBC4PreparedStatement;
import com.sx.utils.GenericTokenParser;
import com.sx.utils.ParameterMappingTokenHandler;
import com.sx.utils.TokenHandler;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shengx
 * @date 2020/2/22 20:37
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        DataSource dataSource = configuration.getDataSource();
        Connection conn = dataSource.getConnection();
        BoundSql boundSql = getBoundSql(mappedStatement.getSql());
        PreparedStatement preparedStatement = conn.prepareStatement(boundSql.getSql());
        Class<?> parameterType = mappedStatement.getParamterType();
        Class<?> resultType = mappedStatement.getResultType();
        List<ParameterMapping> parameterMappings = boundSql.getParamterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String name = parameterMapping.getContent();
            Field declaredField = parameterType.getDeclaredField(name);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1, o);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        List<E> result = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while(resultSet.next()){
            E o = (E) resultType.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultType);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            result.add(o);
        }
        return result;
    }

    @Override
    public void update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        DataSource dataSource = configuration.getDataSource();
        Connection conn = dataSource.getConnection();
        BoundSql boundSql = getBoundSql(mappedStatement.getSql());
        PreparedStatement statement = conn.prepareStatement(boundSql.getSql());
        Class<?> parameterType = mappedStatement.getParamterType();
        Object param = params[0];
        List<ParameterMapping> parameterMappings = boundSql.getParamterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String name = parameterMapping.getContent();
            Field field = parameterType.getDeclaredField(name);
            field.setAccessible(true);
            Object value = field.get(param);
            statement.setObject(i+1, value);
        }
        statement.execute();
        conn.close();
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String parse = genericTokenParser.parse(sql);
        BoundSql boundSql = new BoundSql(parse, tokenHandler.getParameterMappings());
        return boundSql;
    }

    @Override
    public void close() {

    }
}
