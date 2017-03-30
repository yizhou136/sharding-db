package com.zy.shardingdb.config.file;

import com.zy.shardingdb.config.DatabaseClusterConfig;
import org.junit.Test;

/**
 * @author by zy.
 */
public class PropertiesConfigTest {

    @Test
    public void testPropertiesParser(){
        System.out.println((byte)'a');
        System.out.println((byte)'z');
        System.out.println((byte)'A');
        System.out.println((byte)'Z');
        System.out.println((byte)'_');
        String file = "E:\\workspace\\web\\sharding-db\\shardingdb-core\\src\\test\\resources\\shardingdb.properties";
        PropertiesConfigParser propertiesConfigParser = new PropertiesConfigParser();
        DatabaseClusterConfig databaseClusterConfig = propertiesConfigParser.parser(file);
    }
}
