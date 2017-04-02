package com.zy.shardingdb.core.jdbc;

import com.google.common.base.Preconditions;
import com.zy.shardingdb.core.exception.ShardingJdbcException;
import com.zy.shardingdb.core.jdbc.adapter.AbstractDataSourceAdapter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author by zy.
 */
public class ShardingDataSource extends AbstractDataSourceAdapter {
    
    /*private final ShardingProperties shardingProperties;
    
    private final ExecutorEngine executorEngine;
    
    private final ShardingContext shardingContext;
    
    public ShardingDataSource(final ShardingRule shardingRule) {
        this(shardingRule, new Properties());
    }
    
    public ShardingDataSource(final ShardingRule shardingRule, final Properties props) {
        Preconditions.checkNotNull(shardingRule);
        Preconditions.checkNotNull(props);
        shardingProperties = new ShardingProperties(props);
        executorEngine = new ExecutorEngine(shardingProperties);
        try {
            shardingContext = new ShardingContext(shardingRule, new SQLRouteEngine(shardingRule, DatabaseType.valueFrom(getDatabaseProductName(shardingRule))), executorEngine);
        } catch (final SQLException ex) {
            throw new ShardingJdbcException(ex);
        }
    }
    
    private String getDatabaseProductName(final ShardingRule shardingRule) throws SQLException {
        String result = null;
        for (DataSource each : shardingRule.getDataSourceRule().getDataSources()) {
            String databaseProductName;
            if (each instanceof MasterSlaveDataSource) {
                databaseProductName = ((MasterSlaveDataSource) each).getDatabaseProductName();
            } else {
                try (Connection connection = each.getConnection()) {
                    databaseProductName = connection.getMetaData().getDatabaseProductName();
                }
            }
            Preconditions.checkState(null == result || result.equals(databaseProductName), String.format("Database type inconsistent with '%s' and '%s'", result, databaseProductName));
            result = databaseProductName;
        }
        return result;
    }*/
    
    @Override
    public ShardingConnection getConnection() throws SQLException {
        //MetricsContext.init(shardingProperties);
        return null;//new ShardingConnection(shardingContext);
    }
    
    /**
     * 关闭数据源,释放相关资源.
     */
    /*public void shutdown() {
        executorEngine.shutdown();
    }*/
}
