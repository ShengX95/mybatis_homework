package com.sx;

import java.io.InputStream;

/**
 * @author shengx
 * @date 2020/2/22 18:45
 */
public class Resources {
    public static InputStream getResourceAsStream(String path){
        InputStream in = Resources.class.getClassLoader().getResourceAsStream(path);
        return in;
    }
}
