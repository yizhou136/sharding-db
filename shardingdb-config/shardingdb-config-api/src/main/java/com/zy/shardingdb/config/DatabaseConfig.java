package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class DatabaseConfig {
    public static final byte ReadPerm = 0x01;
    public static final byte WeadPerm = 0x10;

    private String logicName;
    private String mappedNames[];
    private Map<String,DatabaseInstanceConfig> mappedDatabaseInstance;

    private byte rwPerm;
    private boolean autoDetectSlaves;
    private RouterConfig defaultRouter;


    private Map<String,TableConfig> tables;

    public String getLogicName() {
        return logicName;
    }

    public void setLogicName(String logicName) {
        this.logicName = logicName;
    }

    public String[] getMappedNames() {
        return mappedNames;
    }

    public void setMappedNames(String[] mappedNames) {
        this.mappedNames = mappedNames;
    }

    public Map<String, DatabaseInstanceConfig> getMappedDatabaseInstance() {
        return mappedDatabaseInstance;
    }

    public void setMappedDatabaseInstance(Map<String, DatabaseInstanceConfig> mappedDatabaseInstance) {
        this.mappedDatabaseInstance = mappedDatabaseInstance;
    }

    public byte getRwPerm() {
        return rwPerm;
    }

    public void setRwPerm(byte rwPerm) {
        this.rwPerm = rwPerm;
    }

    public boolean isAutoDetectSlaves() {
        return autoDetectSlaves;
    }

    public void setAutoDetectSlaves(boolean autoDetectSlaves) {
        this.autoDetectSlaves = autoDetectSlaves;
    }

    public RouterConfig getDefaultRouter() {
        return defaultRouter;
    }

    public void setDefaultRouter(RouterConfig defaultRouter) {
        this.defaultRouter = defaultRouter;
    }

    public Map<String, TableConfig> getTables() {
        return tables;
    }

    public void setTables(Map<String, TableConfig> tables) {
        this.tables = tables;
    }


    public String generatePropertiesKey(String propertyName){
        return String.format("database.%s.%s", getLogicName(), propertyName);
    }
}