package com.mufan.utils.jsonStrategy.json;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * Created by aswe on 2016/7/25.
 */
public class JsonConfigStrategy {
    private JsonConfigStrategy() {
    }

    private static JsonConfigStrategy sonConfigStrategy;

    public static JsonConfig getSubGroupStrategy() {
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object o, String s, Object o1) {
                if (s.equals("subGroup")) return true;
                return false;
            }
        });
        return config;
    }
    public static JsonConfig getFilmStrategy() {
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object o, String s, Object o1) {
                if (s.equals("films")) return true;
                return false;
            }
        });
        return config;
    }

    /*public static JsonConfigStrategy getSubGroupStrategy(){
        if (sonConfigStrategy == null) {
            synchronized (JsonConfigStrategy.class) {
                if (sonConfigStrategy == null) {
                    sonConfigStrategy = new JsonConfigStrategy();
                }
            }
        }
        return sonConfigStrategy;
    }*/

}
