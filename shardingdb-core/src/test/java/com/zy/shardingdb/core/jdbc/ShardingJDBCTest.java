package com.zy.shardingdb.core.jdbc;

import com.zy.shardingdb.config.GlobalConfig;
import com.zy.shardingdb.config.file.PropertiesConfigParser;
import org.junit.Test;

/**
 * @author by zy.
 */
public class ShardingJDBCTest {

    @Test
    public void testSelect(){

    }


    private GlobalConfig genGlobalConfig(){
        String file = "E:\\workspace\\web\\sharding-db\\shardingdb-core\\src\\test\\resources\\shardingdb.properties";
        PropertiesConfigParser propertiesConfigParser = new PropertiesConfigParser();
        return propertiesConfigParser.parser(file);
    }
}
