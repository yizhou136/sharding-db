package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class TableConfig {
    private String logicName;
    private Map<String,TableConfig> actualTables;

    private RouterConfig dbRouter;
    private RouterConfig tbRouter;

    private String executer;

    private DatabaseConfig databaseConfig;
}
