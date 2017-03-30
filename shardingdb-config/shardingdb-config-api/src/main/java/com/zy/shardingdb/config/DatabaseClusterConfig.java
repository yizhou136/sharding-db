package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class DatabaseClusterConfig {
    public final static String DefaultPropertiesKey = "databases";


    private Map<String,DatabaseConfig> databaseClusterMap;

    public Map<String, DatabaseConfig> getDatabaseClusterMap() {
        return databaseClusterMap;
    }

    public void setDatabaseClusterMap(Map<String, DatabaseConfig> databaseClusterMap) {
        this.databaseClusterMap = databaseClusterMap;
    }

    public String generateDefaultPropertiesKey(){
        return DefaultPropertiesKey;
    }
    public String generatePropertiesKey(String propertyName){
        return propertyName;
    }
}
