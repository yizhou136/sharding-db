package com.zy.shardingdb.config;

/**
 * @author by zy.
 */
public class GlobalConfig {

    private DatabaseClusterConfig databaseClusterConfig;
    private RoutersConfig routersConfig;

    public DatabaseClusterConfig getDatabaseClusterConfig() {
        return databaseClusterConfig;
    }

    public void setDatabaseClusterConfig(DatabaseClusterConfig databaseClusterConfig) {
        this.databaseClusterConfig = databaseClusterConfig;
    }

    public RoutersConfig getRoutersConfig() {
        return routersConfig;
    }

    public void setRoutersConfig(RoutersConfig routersConfig) {
        this.routersConfig = routersConfig;
    }
}
