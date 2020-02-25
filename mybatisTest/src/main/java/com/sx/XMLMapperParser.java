package com.sx;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.print.Doc;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shengx
 * @date 2020/2/22 19:14
 */
public class XMLMapperParser {
    InputStream in;

    public XMLMapperParser(InputStream in) {
        this.in = in;
    }

    public void parse(Configuration config) throws Exception {
        Map<String, MappedStatement> map = config.getMap();
        Document document = new SAXReader().read(in);
        Element rootElem = document.getRootElement();
        List<Element> selectElems = rootElem.selectNodes("//select|//insert|//update|//delete");
        String namespace = rootElem.attributeValue("namespace");
        for (Element selectElem : selectElems) {
            String name = selectElem.getName();
            boolean isSelect = false;
            if(name.equals("select"))
                isSelect = true;
            String id = selectElem.attributeValue("id");
            String sql = selectElem.getText();
            String paramterType = selectElem.attributeValue("paramterType");
            String resultType = selectElem.attributeValue("resultType");
            MappedStatement mappedStatement = new MappedStatement();
            if(paramterType != null){
                Class<?> paramClass = Class.forName(paramterType);
                mappedStatement.setParamterType(paramClass);
            }
            if(resultType != null){
                Class<?> resultClass = Class.forName(resultType);
                mappedStatement.setResultType(resultClass);
            }
            mappedStatement.setId(id);
            mappedStatement.setSql(sql);
            mappedStatement.setSelect(isSelect);
            map.put(namespace + "." + id, mappedStatement);
        }
        System.out.println(map);
    }
}
