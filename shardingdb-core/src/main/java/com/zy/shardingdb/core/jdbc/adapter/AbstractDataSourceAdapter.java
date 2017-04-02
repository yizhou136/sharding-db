package com.zy.shardingdb.core.jdbc.adapter;


import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author by zy.
 */
public abstract class AbstractDataSourceAdapter extends AbstractUnsupportedOperationDataSource {
    
    private PrintWriter logWriter = new PrintWriter(System.out);

    @Override
    protected boolean cantInvokedNow() {
        return true;
    }

    @Override
    public final PrintWriter getLogWriter() throws SQLException {
        return logWriter;
    }
    
    @Override
    public final void setLogWriter(final PrintWriter out) throws SQLException {
        this.logWriter = out;
    }
    
    @Override
    public final Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
    
    @Override
    public final Connection getConnection(final String username, final String password) throws SQLException {
        return getConnection();
    }
}
