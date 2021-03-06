package com.zy.shardingdb.core.jdbc;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.zy.shardingdb.core.jdbc.adapter.AbstractStatementAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author by zy.
 */
public class ShardingStatement extends AbstractStatementAdapter {
    
    /*private static final Function<BackendStatementWrapper, Statement> TRANSFORM_FUNCTION = new Function<BackendStatementWrapper, Statement>() {
        @Override
        public Statement apply(final BackendStatementWrapper input) {
            return input.getStatement();
        }
    };*/
    

    /*private final ShardingConnection shardingConnection;
    private final int resultSetType;
    private final int resultSetConcurrency;

    private final int resultSetHoldability;*/
    
    /*private final Deque<List<BackendStatementWrapper>> cachedRoutedStatements = Lists.newLinkedList();
    private MergeContext mergeContext;
    private ResultSet currentResultSet;
    private GeneratedKeyContext generatedKeyContext;*/
    
    private ResultSet generatedKeyResultSet;
    
    ShardingStatement(final ShardingConnection shardingConnection) {
        this(shardingConnection, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    
    ShardingStatement(final ShardingConnection shardingConnection, final int resultSetType, final int resultSetConcurrency) {
        this(shardingConnection, resultSetType, resultSetConcurrency, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    
    public ShardingStatement(final ShardingConnection shardingConnection, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) {
        /*super(Statement.class);
        this.shardingConnection = shardingConnection;
        this.resultSetType = resultSetType;
        this.resultSetConcurrency = resultSetConcurrency;
        this.resultSetHoldability = resultSetHoldability;
        cachedRoutedStatements.add(new LinkedList<BackendStatementWrapper>());
        cachedRoutedStatements.add(new LinkedList<BackendStatementWrapper>());*/
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    
    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        ResultSet rs = null;
        /*try {
            rs = ResultSetFactory.getResultSet(generateExecutor(sql).executeQuery(), mergeContext);
        } finally {
            clearRouteContext();
        }
        setCurrentResultSet(rs);*/
        return rs;
    }
    
    @Override
    public int executeUpdate(final String sql) throws SQLException {
        try {
            return 0;//generateExecutor(sql).executeUpdate();
        } finally {
            clearRouteContext();
        }
    }
    
    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        /*try {
            return generateExecutor(sql).executeUpdate(autoGeneratedKeys);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setAutoGeneratedKeys(autoGeneratedKeys);
            }
            
            clearRouteContext();
        }*/
        return 0;
    }
    
    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        /*try {
            return generateExecutor(sql).executeUpdate(columnIndexes);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setColumnIndexes(columnIndexes);
            }
            clearRouteContext();
        }*/
        return 0;
    }
    
    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        /*try {
            return generateExecutor(sql).executeUpdate(columnNames);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setColumnNames(columnNames);
            }
            clearRouteContext();
        }*/
        return 0;
    }
    
    @Override
    public boolean execute(final String sql) throws SQLException {
        /*try {
            return generateExecutor(sql).execute();
        } finally {
            clearRouteContext();
        }*/
        return true;
    }
    
    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        /*try {
            return generateExecutor(sql).execute(autoGeneratedKeys);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setAutoGeneratedKeys(autoGeneratedKeys);
            }
            clearRouteContext();
        }*/
        return false;
    }
    
    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        /*try {
            return generateExecutor(sql).execute(columnIndexes);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setColumnIndexes(columnIndexes);
            }
            clearRouteContext();
        }*/
        return false;
    }
    
    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        /*try {
            return generateExecutor(sql).execute(columnNames);
        } finally {
            if (null != generatedKeyContext) {
                generatedKeyContext.setColumnNames(columnNames);
            }
            clearRouteContext();
        }*/
        return false;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public final ResultSet getGeneratedKeys() throws SQLException {
        /*if (null != generatedKeyResultSet) {
            return generatedKeyResultSet;
        }
        if (null == generatedKeyContext || generatedKeyContext.getColumnNameToIndexMap().isEmpty()) {
            Collection<? extends Statement> routedStatements = getRoutedStatements();
            if (1 == routedStatements.size()) {
                return generatedKeyResultSet = routedStatements.iterator().next().getGeneratedKeys();
            }
        }
        if (Statement.RETURN_GENERATED_KEYS != generatedKeyContext.getAutoGeneratedKeys() && null == generatedKeyContext.getColumnIndexes()
                && null == generatedKeyContext.getColumnNames()) {
            
            return generatedKeyResultSet = new GeneratedKeysResultSet();
        }*/
        return null;//generatedKeyResultSet = new GeneratedKeysResultSet(generateAutoIncrementTable(), generatedKeyContext.getColumnNameToIndexMap(), this);
    }
    
    private Table<Integer, Integer, Object> generateAutoIncrementTable() {
        /*if (null != generatedKeyContext.getColumnIndexes()) {
            return subTable(generatedKeyContext.getColumnIndexes());
        } else if (null != generatedKeyContext.getColumnNames()) {
            List<Integer> columnIndexes = new ArrayList<>(generatedKeyContext.getColumnNames().length);
            for (String each : generatedKeyContext.getColumnNames()) {
                if (!generatedKeyContext.getColumnNameToIndexMap().containsKey(each)) {
                    continue;
                }
                columnIndexes.add(generatedKeyContext.getColumnNameToIndexMap().get(each) + 1);
            }
            int[] parameter = new int[columnIndexes.size()];
            int index = 0;
            for (Integer each : columnIndexes) {
                parameter[index++] = each;
            }
            return subTable(parameter);
        }*/
        return null;//generatedKeyContext.getValueTable();
    }
    
    private Table<Integer, Integer, Object> subTable(final int[] columnIndexes) {
        /*Table<Integer, Integer, Object> result = TreeBasedTable.create();
        for (int each : columnIndexes) {
            for (Map.Entry<Integer, Object> eachEntry : generatedKeyContext.getValueTable().column(each - 1).entrySet()) {
                result.put(eachEntry.getKey(), each - 1, eachEntry.getValue());
            }
        }*/
        return null;//result;
    }
    
    protected void clearRouteContext() throws SQLException {
        /*setCurrentResultSet(null);
        List<BackendStatementWrapper> firstList = cachedRoutedStatements.pollFirst();
        cachedRoutedStatements.getFirst().addAll(firstList);
        firstList.clear();
        cachedRoutedStatements.addLast(firstList);
        generatedKeyResultSet = null;*/
    }
    
    /*private StatementExecutor generateExecutor(final String sql) throws SQLException {
        StatementExecutor result = new StatementExecutor(shardingConnection.getShardingContext().getExecutorEngine());
        SQLRouteResult sqlRouteResult = shardingConnection.getShardingContext().getSqlRouteEngine().route(sql);
        generatedKeyContext = sqlRouteResult.getGeneratedKeyContext();
        mergeContext = sqlRouteResult.getMergeContext();
        for (SQLExecutionUnit each : sqlRouteResult.getExecutionUnits()) {
            Statement statement = getStatement(shardingConnection.getConnection(each.getDataSource(), sqlRouteResult.getSqlStatementType()), each.getSql());
            replayMethodsInvocation(statement);
            result.addStatement(new StatementExecutorWrapper(statement, each));
        }
        return result;
    }*/
    
    protected Statement getStatement(final Connection connection, final String sql) throws SQLException {
        /*BackendStatementWrapper statement = null;
        for (Iterator<BackendStatementWrapper> iterator = cachedRoutedStatements.getFirst().iterator(); iterator.hasNext();) {
            BackendStatementWrapper each = iterator.next();
            if (each.isBelongTo(connection, sql)) {
                statement = each;
                iterator.remove();
            }
        }
        if (null == statement) {
            statement = generateStatement(connection, sql);
        }
        cachedRoutedStatements.getLast().add(statement);*/
        return null;//statement.getStatement();
    }
    
    /*protected BackendStatementWrapper generateStatement(final Connection connection, final String sql) throws SQLException {
        Statement result;
        if (0 == resultSetHoldability) {
            result = connection.createStatement(resultSetType, resultSetConcurrency);
        } else {
            result = connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        return new BackendStatementWrapper(result);
    }*/
    
    @Override
    public ResultSet getResultSet() throws SQLException {
        /*if (null != currentResultSet) {
            return currentResultSet;
        }
        List<ResultSet> resultSets = new ArrayList<>(getRoutedStatements().size());
        if (getRoutedStatements().size() == 1) {
            currentResultSet = getRoutedStatements().iterator().next().getResultSet();
            return currentResultSet;
        }
        for (Statement each : getRoutedStatements()) {
            resultSets.add(each.getResultSet());
        }
        currentResultSet = ResultSetFactory.getResultSet(resultSets, mergeContext);*/
        return null;//currentResultSet;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return 0;
    }

    @Override
    public int getResultSetType() throws SQLException {
        return 0;
    }

    @Override
    protected void clearRouteStatements() {
        //cachedRoutedStatements.getFirst().clear();
        //cachedRoutedStatements.getLast().clear();
    }
    
    @Override
    public Collection<? extends Statement> getRoutedStatements() {
        return null;// Lists.newArrayList(Iterators.concat(Iterators.transform(cachedRoutedStatements.getFirst().iterator(), TRANSFORM_FUNCTION),
                //Iterators.transform(cachedRoutedStatements.getLast().iterator(), TRANSFORM_FUNCTION)));
    }
}
