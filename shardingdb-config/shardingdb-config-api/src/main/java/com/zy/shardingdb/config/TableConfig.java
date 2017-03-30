package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class TableConfig {
    private String logicName;
    private String[] mappedNames;

    private RouterConfig dbRouter;
    private RouterConfig tbRouter;

    private String executer;

    private DatabaseConfig databaseConfig;

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

    public RouterConfig getDbRouter() {
        return dbRouter;
    }

    public void setDbRouter(RouterConfig dbRouter) {
        this.dbRouter = dbRouter;
    }

    public RouterConfig getTbRouter() {
        return tbRouter;
    }

    public void setTbRouter(RouterConfig tbRouter) {
        this.tbRouter = tbRouter;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
}
