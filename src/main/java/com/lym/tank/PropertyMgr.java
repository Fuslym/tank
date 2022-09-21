package com.lym.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author li yamin
 * @create 2022-09-21
 */
public class PropertyMgr {
    static Properties props = new Properties();

    static{
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if (props == null)
            return null;
        return props.get(key);
    }
}
