package com.zy.shardingdb.core.jdbc.adapter;

import com.google.common.base.Preconditions;
import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationResultSet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * @author by zy.
 */
public abstract class AbstractResultSetAdapter extends AbstractUnsupportedOperationResultSet {

    private final List<ResultSet> resultSets;

    private final Map<String, Integer> columnLabelIndexMap;

    private boolean closed;
    
    public AbstractResultSetAdapter(final List<ResultSet> resultSets) throws SQLException {
        Preconditions.checkArgument(!resultSets.isEmpty());
        this.resultSets = resultSets;
        columnLabelIndexMap = generateColumnLabelIndexMap();
    }
    
    private Map<String, Integer> generateColumnLabelIndexMap() throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSets.get(0).getMetaData();
        Map<String, Integer> result = null;//new CaseInsensitiveMap<>(resultSetMetaData.getColumnCount());
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            result.put(resultSetMetaData.getColumnLabel(i), i);
        }
        return result;
    }
    
    @Override
    public final void close() throws SQLException {
        /*SQLUtil.safeInvoke(resultSets, new ThrowableSQLExceptionMethod<ResultSet>() {
            @Override
            public void apply(final ResultSet object) throws SQLException {
                object.close();
            }
        });*/
        closed = true;
    }
    
    @Override
    public final boolean isClosed() throws SQLException {
        return closed;
    }
    
    @Override
    public final void setFetchDirection(final int direction) throws SQLException {
        for (ResultSet each : resultSets) {
            each.setFetchDirection(direction);
        }
    }
    
    @Override
    public final void setFetchSize(final int rows) throws SQLException {
        for (ResultSet each : resultSets) {
            each.setFetchSize(rows);
        }
    }
}
