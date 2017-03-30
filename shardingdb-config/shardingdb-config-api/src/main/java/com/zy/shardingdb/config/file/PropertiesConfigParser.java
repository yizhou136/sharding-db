package com.zy.shardingdb.config.file;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Multimap;
import com.zy.shardingdb.config.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author by zy.
 */
public class PropertiesConfigParser {
    /*private String propertiesFile;

    public PropertiesConfigParser(String propertiesFile){
        this.propertiesFile = propertiesFile;
    }*/

    public DatabaseClusterConfig parser(String propertiesFile){
        Preconditions.checkNotNull(Strings.emptyToNull(propertiesFile));

        DatabaseClusterConfig databaseClusterConfig = null;
        try {
            InputStream inputStream = new FileInputStream(propertiesFile);
            Properties properties = new Properties();
            properties.load(inputStream);
            databaseClusterConfig = parser(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return databaseClusterConfig;
    }

    public DatabaseClusterConfig parser(Properties properties){
        DatabaseClusterConfig databaseClusterConfig = new DatabaseClusterConfig();
        String databases = properties.getProperty(databaseClusterConfig.generateDefaultPropertiesKey()
                                ,"");
        Map<String, String[]> multimap = AbstractConfig.parserRangeNames(databases);
        for (String databaseLogicName : multimap.keySet()){
            String [] mappedDatabaseNames = multimap.get(databaseLogicName);
            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.setLogicName(databaseLogicName);
            databaseConfig.setMappedNames(mappedDatabaseNames);

            String rwPerm = properties.getProperty(databaseConfig.generatePropertiesKey("rw_perm"));
            if (rwPerm != null){
                //todo
            }

            //for database instance
            for (String mappedDatabaseName : mappedDatabaseNames){
                DatabaseInstanceConfig databaseInstanceConfig = new DatabaseInstanceConfig();
                databaseInstanceConfig.setMappedName(mappedDatabaseName);
                String url = properties.getProperty(databaseInstanceConfig.generatePropertiesKey("url"));
                String user = properties.getProperty(databaseInstanceConfig.generatePropertiesKey("user"));
                String pwd = properties.getProperty(databaseInstanceConfig.generatePropertiesKey("pwd"));
                if (url == null || user == null || pwd == null)
                    continue;
                databaseInstanceConfig.setUrl(url);
                databaseInstanceConfig.setUser(user);
                databaseInstanceConfig.setPwd(pwd);

                Map<String, String[]> mappedSlaveNamesMap = AbstractConfig.parserRangeNames(
                        properties.getProperty(databaseInstanceConfig.generatePropertiesKey("slaves")));
                for (String slaveName : mappedSlaveNamesMap.keySet()){
                    String [] mappedSlaveNames = mappedSlaveNamesMap.get(slaveName);
                    for (String mappedSlaveName : mappedSlaveNames) {
                        DatabaseInstanceConfig slaveInstanceConfig = new DatabaseInstanceConfig();
                        slaveInstanceConfig.setMappedName(mappedSlaveName);
                        slaveInstanceConfig.setMappedSlaveNames(mappedSlaveNames);
                        url = properties.getProperty(slaveInstanceConfig.generatePropertiesKey("url"));
                        user = properties.getProperty(slaveInstanceConfig.generatePropertiesKey("user"));
                        pwd = properties.getProperty(slaveInstanceConfig.generatePropertiesKey("pwd"));
                        if (url == null || user == null || pwd == null)
                            continue;
                        slaveInstanceConfig.setUrl(url);
                        slaveInstanceConfig.setUser(user);
                        slaveInstanceConfig.setPwd(pwd);

                        Map slaves = databaseInstanceConfig.getSlaves();
                        if (slaves == null) {
                            slaves = new HashMap<String, DatabaseInstanceConfig>();
                            databaseInstanceConfig.setSlaves(slaves);
                        }
                        slaves.put(mappedSlaveName, slaveInstanceConfig);
                    }
                }

                //for database tables
                Map<String, String[]> databaseTablesMap = AbstractConfig.parserRangeNames(
                        properties.getProperty(databaseConfig.generatePropertiesKey("tables")));
                for (String tableName : databaseTablesMap.keySet()){
                    TableConfig tableConfig = new TableConfig();
                    tableConfig.setLogicName(tableName);
                    tableConfig.setMappedNames(databaseTablesMap.get(tableName));
                    tableConfig.setDatabaseConfig(databaseConfig);
                    Map tables = databaseConfig.getTables();
                    if (tables == null){
                        tables = new HashMap();
                        databaseConfig.setTables(tables);
                    }
                    tables.put(tableName, tableConfig);
                }

                Map instanceMap = databaseConfig.getMappedDatabaseInstance();
                if (instanceMap == null){
                    instanceMap = new HashMap();
                    databaseConfig.setMappedDatabaseInstance(instanceMap);
                }
                instanceMap.put(mappedDatabaseName, databaseInstanceConfig);
            }

            Map clusterMap = databaseClusterConfig.getDatabaseClusterMap();
            if (clusterMap == null){
                clusterMap = new HashMap();
                databaseClusterConfig.setDatabaseClusterMap(clusterMap);
            }
            clusterMap.put(databaseLogicName, databaseConfig);
        }
        return databaseClusterConfig;
    }
}
