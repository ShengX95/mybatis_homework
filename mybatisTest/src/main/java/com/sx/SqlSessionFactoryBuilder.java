package com.sx;

import java.io.InputStream;

/**
 * @author shengx
 * @date 2020/2/22 19:04
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactory build(InputStream in) throws Exception {
        Configuration config = new Configuration();
        XMLConfigParser xmlConfigParser = new XMLConfigParser(in);
        xmlConfigParser.parse(config);
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(config);
        return sqlSessionFactory;
    }
}
