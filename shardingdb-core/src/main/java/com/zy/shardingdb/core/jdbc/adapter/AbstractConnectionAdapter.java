package com.zy.shardingdb.core.jdbc.adapter;

import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Collection;

/**
 * @author by zy.
 */
public abstract class AbstractConnectionAdapter extends AbstractUnsupportedOperationConnection {
    
    private boolean autoCommit = true;
    
    private boolean readOnly = true;
    
    private boolean closed;
    
    private int transactionIsolation = TRANSACTION_READ_UNCOMMITTED;

    public AbstractConnectionAdapter(){
        detectConditionMethod(AbstractConnectionAdapter.class);
    }
    
    protected abstract Collection<Connection> getConnections();


    @Override
    protected boolean cantInvokedNow() {
        return getConnections().isEmpty();
    }

    @Override
    public final boolean getAutoCommit() throws SQLException {
        return autoCommit;
    }
    
    @Override
    public final void setAutoCommit(final boolean autoCommit) throws SQLException {
        this.autoCommit = autoCommit;
        if (cantInvokedNow()) {
            recordMethodInvocation("setAutoCommit",
                    new Class[] {boolean.class}, new Object[] {autoCommit});
            return;
        }

        for (Connection each : getConnections()) {
            each.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public final void commit() throws SQLException {
        for (Connection each : getConnections()) {
            each.commit();
        }
    }
    
    @Override
    public final void rollback() throws SQLException {
        /*SQLUtil.safeInvoke(getConnections(), new ThrowableSQLExceptionMethod<Connection>() {
            @Override
            public void apply(final Connection object) throws SQLException {
                object.rollback();
            }
        });*/
    }
    
    @Override
    public void close() throws SQLException {
        /*SQLUtil.safeInvoke(getConnections(), new ThrowableSQLExceptionMethod<Connection>() {
            @Override
            public void apply(final Connection object) throws SQLException {
                object.close();
            }
        });*/
        closed = true;
        //MetricsContext.clear();
    }
    
    @Override
    public final boolean isClosed() throws SQLException {
        return closed;
    }
    
    @Override
    public final boolean isReadOnly() throws SQLException {
        return readOnly;
    }
    
    @Override
    public final void setReadOnly(final boolean readOnly) throws SQLException {
        this.readOnly = readOnly;
        if (cantInvokedNow()) {
            recordMethodInvocation("setReadOnly",
                    new Class[] {boolean.class}, new Object[] {readOnly});
            return;
        }

        for (Connection each : getConnections()) {
            each.setReadOnly(readOnly);
        }
    }
    
    @Override
    public final int getTransactionIsolation() throws SQLException {
        return transactionIsolation;
    }
    
    @Override
    public final void setTransactionIsolation(final int level) throws SQLException {
        transactionIsolation = level;
        if (getConnections().isEmpty()) {
            recordMethodInvocation("setTransactionIsolation",
                    new Class[] {int.class}, new Object[] {level});
            return;
        }
        for (Connection each : getConnections()) {
            each.setTransactionIsolation(level);
        }
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }
    
    @Override
    public void clearWarnings() throws SQLException {
    }
    
    @Override
    public final int getHoldability() throws SQLException {
        return ResultSet.CLOSE_CURSORS_AT_COMMIT;
    }
    
    @Override
    public final void setHoldability(final int holdability) throws SQLException {
    }
}
