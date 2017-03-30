package com.zy.shardingdb.config;

import java.util.Map;

/**
 * @author by zy.
 */
public class DatabaseInstanceConfig{

    private String mappedName;
    private String url;
    private String driver;
    private String user;
    private String pwd;

    private String mappedSlaveNames[];
    private Map<String,DatabaseInstanceConfig> slaves;

    public String getMappedName() {
        return mappedName;
    }

    public void setMappedName(String mappedName) {
        this.mappedName = mappedName;
    }

    public String[] getMappedSlaveNames() {
        return mappedSlaveNames;
    }

    public void setMappedSlaveNames(String[] mappedSlaveNames) {
        this.mappedSlaveNames = mappedSlaveNames;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Map<String, DatabaseInstanceConfig> getSlaves() {
        return slaves;
    }

    public void setSlaves(Map<String, DatabaseInstanceConfig> slaves) {
        this.slaves = slaves;
    }



    public String generatePropertiesKey(String propertyName){
        return String.format("database.%s.%s", getMappedName(), propertyName);
    }
}