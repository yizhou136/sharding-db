package com.zy.shardingdb.config;

/**
 * @author by zy.
 */
public class DBConfig extends AbstractConfig{
    public static final byte ReadPerm = 0x01;
    public static final byte WeadPerm = 0x10;

    private String url;
    private String driver;
    private String user;
    private String pwd;

    private byte rwPerm;
    private String databasePattern;


    private String databases[];


}