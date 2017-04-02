package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class RoutersConfig {
    public final static String DefaultPropertiesKey = "routers";

    private Map<String,RouterConfig> routersConfig;

    public Map<String, RouterConfig> getRoutersConfig() {
        return routersConfig;
    }

    public void setRoutersConfig(Map<String, RouterConfig> routersConfig) {
        this.routersConfig = routersConfig;
    }

    public static final String generateDefaultPropertiesKey(){
        return DefaultPropertiesKey;
    }
    public String generatePropertiesKey(String propertyName){
        return propertyName;
    }
}
