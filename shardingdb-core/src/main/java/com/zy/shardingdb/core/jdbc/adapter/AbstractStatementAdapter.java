package com.zy.shardingdb.core.jdbc.adapter;

import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Collection;


/**
 * @author by zy.
 */
public abstract class AbstractStatementAdapter extends AbstractUnsupportedOperationStatement {
    private boolean closed;
    
    private boolean poolable;
    
    private int fetchSize;

    /*public AbstractStatementAdapter(Class<? extends Statement> recordTargetClass) {
        this.recordTargetClass = recordTargetClass;
    }*/

    protected abstract void clearRouteStatements();

    @Override
    protected boolean cantInvokedNow() {
        return cantInvokedNow();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void close() throws SQLException {
        /*SQLUtil.safeInvoke(getRoutedStatements(), new ThrowableSQLExceptionMethod() {
            @Override
            public void apply(final Object object) throws SQLException {
                ((Statement) object).close();
            }
        });*/
        closed = true;
        clearRouteStatements();
    }
    
    @Override
    public final boolean isClosed() throws SQLException {
        return closed;
    }
    
    @Override
    public final boolean isPoolable() throws SQLException {
        return poolable;
    }
    
    @Override
    public final void setPoolable(final boolean poolable) throws SQLException {
        this.poolable = poolable;
        if (cantInvokedNow()) {
            recordMethodInvocation("setPoolable",
                    new Class[] {boolean.class}, new Object[] {poolable});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setPoolable(poolable);
        }
    }
    
    @Override
    public final int getFetchSize() throws SQLException {
        return fetchSize;
    }
    
    @Override
    public final void setFetchSize(final int rows) throws SQLException {
        this.fetchSize = rows;
        if (cantInvokedNow()) {
            recordMethodInvocation("setFetchSize",
                    new Class[] {int.class}, new Object[] {rows});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setFetchSize(rows);
        }
    }
    
    @Override
    public final void setEscapeProcessing(final boolean enable) throws SQLException {
        if (cantInvokedNow()) {
            recordMethodInvocation("setEscapeProcessing",
                    new Class[] {boolean.class}, new Object[] {enable});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setEscapeProcessing(enable);
        }
    }
    
    @Override
    public final void cancel() throws SQLException {
        for (Statement each : getRoutedStatements()) {
            each.cancel();
        }
    }
    
    @Override
    public final void setCursorName(final String name) throws SQLException {
        if (cantInvokedNow()) {
            recordMethodInvocation("setCursorName",
                    new Class[] {String.class}, new Object[] {name});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setCursorName(name);
        }
    }
    
    @Override
    public final int getUpdateCount() throws SQLException {
        long result = 0;
        boolean hasResult = false;
        for (Statement each : getRoutedStatements()) {
            if (each.getUpdateCount() > -1) {
                hasResult = true;
            }
            result += each.getUpdateCount();
        }
        if (result > Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        }
        return hasResult ? Long.valueOf(result).intValue() : -1;
    }
    
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }
    
    @Override
    public void clearWarnings() throws SQLException {
    }
    
    /* 
     * 只有存储过程会出现多结果集, 因此不支持.
     */
    @Override
    public final boolean getMoreResults() throws SQLException {
        return false;
    }
    
    @Override
    public final boolean getMoreResults(final int current) throws SQLException {
        return false;
    }
    
    @Override
    public final int getMaxFieldSize() throws SQLException {
        return cantInvokedNow() ? 0 : getRoutedStatements().iterator().next().getMaxFieldSize();
    }
    
    @Override
    public final void setMaxFieldSize(final int max) throws SQLException {
        if (cantInvokedNow()) {
            recordMethodInvocation("setMaxFieldSize",
                    new Class[] {int.class}, new Object[] {max});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setMaxFieldSize(max);
        }
    }
    
    // TODO 未来需要确认MaxRows是否在多数据库情况下需要特殊处理,以满足校验需要. 如: 10个statement可能需要将MaxRows / 10
    @Override
    public final int getMaxRows() throws SQLException {
        return cantInvokedNow() ? -1 : getRoutedStatements().iterator().next().getMaxRows();
    }
    
    @Override
    public final void setMaxRows(final int max) throws SQLException {
        if (cantInvokedNow()) {
            recordMethodInvocation("setMaxRows",
                    new Class[] {int.class}, new Object[] {max});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setMaxRows(max);
        }
    }
    
    @Override
    public final int getQueryTimeout() throws SQLException {
        return cantInvokedNow() ? 0 : getRoutedStatements().iterator().next().getQueryTimeout();
    }
    
    @Override
    public final void setQueryTimeout(final int seconds) throws SQLException {
        if (cantInvokedNow()) {
            recordMethodInvocation("setQueryTimeout",
                    new Class[] {int.class}, new Object[] {seconds});
            return;
        }
        for (Statement each : getRoutedStatements()) {
            each.setQueryTimeout(seconds);
        }
    }
    
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        if (1 == getRoutedStatements().size()) {
            return getRoutedStatements().iterator().next().getGeneratedKeys();
        }
        throw new IllegalStateException("Cannot call getGeneratedKeys if sharding statements more than 1.");
    }
    
    /**
     * 获取路由的静态语句对象集合.
     * 
     * @return 路由的静态语句对象集合
     */
    protected abstract Collection<? extends Statement> getRoutedStatements();
}
