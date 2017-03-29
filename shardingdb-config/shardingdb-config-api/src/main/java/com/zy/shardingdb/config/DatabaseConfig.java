package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class DatabaseConfig {
    public static final byte ReadPerm = 0x01;
    public static final byte WeadPerm = 0x10;

    private String logicName;
    private Map<String,DatabaseInstanceConfig> masters;

    private byte rwPerm;
    private boolean autoDetectSlaves;
    private RouterConfig defaultRouter;


    private Map<String,TableConfig> tables;
}