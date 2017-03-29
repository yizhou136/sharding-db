package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class DatabaseInstanceConfig extends DatabaseConfig{
    private String name;
    private String url;
    private String driver;
    private String user;
    private String pwd;

    private Map<String,DatabaseInstanceConfig> slaves;

}