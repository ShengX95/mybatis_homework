package com.sx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author shengx
 * @date 2020/2/22 19:11
 */
public class XMLConfigParser {
    InputStream in;
    public XMLConfigParser(InputStream in) {
        this.in = in;
    }

    public void parse(Configuration config) throws Exception {
        Document document = new SAXReader().read(in);
        Element rootEle = document.getRootElement();
        List<Element> props = rootEle.selectNodes("//Property");
        Properties pro = new Properties();
        for (Element prop : props) {
            String name = prop.attributeValue("name");
            String value = prop.attributeValue("value");
            pro.setProperty(name, value);
        }

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(pro.getProperty("driver"));
        dataSource.setJdbcUrl(pro.getProperty("jdbcUrl"));
        dataSource.setUser(pro.getProperty("username"));
        dataSource.setPassword(pro.getProperty("password"));

        config.setDataSource(dataSource);

        List<Element> mapperElems = rootEle.selectNodes("//Mapper");

        for (Element mapperElem : mapperElems) {
            String path = mapperElem.attributeValue("resource");
            InputStream in = Resources.getResourceAsStream(path);
            XMLMapperParser xmlMapperParser = new XMLMapperParser(in);
            xmlMapperParser.parse(config);
        }
    }
}
