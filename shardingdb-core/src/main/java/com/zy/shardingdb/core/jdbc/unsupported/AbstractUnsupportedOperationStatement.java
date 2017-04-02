package com.zy.shardingdb.core.jdbc.unsupported;

import com.zy.shardingdb.core.jdbc.adapter.WrapperAdapter;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

/**
 * @author by zy.
 */
public abstract class AbstractUnsupportedOperationStatement extends WrapperAdapter implements Statement {
    
    @Override
    public final int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("getFetchDirection");
    }
    
    @Override
    public final void setFetchDirection(final int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("setFetchDirection");
    }
    
    @Override
    public final void addBatch(final String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("addBatch sql");
    }
    
    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("clearBatch");
    }
    
    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("executeBatch");
    }
    
    @Override
    public final void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("closeOnCompletion");
    }
    
    @Override
    public final boolean isCloseOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("isCloseOnCompletion");
    }
}
