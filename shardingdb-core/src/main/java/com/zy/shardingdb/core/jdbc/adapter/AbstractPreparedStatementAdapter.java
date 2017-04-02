package com.zy.shardingdb.core.jdbc.adapter;

import com.zy.shardingdb.core.jdbc.ShardingConnection;
import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationPreparedStatement;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 * @author by zy.
 */
public abstract class AbstractPreparedStatementAdapter extends AbstractUnsupportedOperationPreparedStatement {

    /*public AbstractPreparedStatementAdapter(){
        detectConditionMethod(PreparedStatement.class);
    }*/
    
    protected AbstractPreparedStatementAdapter(final ShardingConnection shardingConnection, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability) {
        super(shardingConnection, resultSetType, resultSetConcurrency, resultSetHoldability);
    }
    
    @Override
    public final void setNull(final int parameterIndex, final int sqlType) throws SQLException {
        setParameter(parameterIndex, "setNull", new Class[]{int.class, int.class}, parameterIndex, sqlType);
    }
    
    @Override
    public final void setNull(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
        setParameter(parameterIndex, "setNull", new Class[]{int.class, int.class, String.class}, parameterIndex, sqlType, typeName);
    }
    
    @Override
    public final void setBoolean(final int parameterIndex, final boolean x) throws SQLException {
        setParameter(parameterIndex, "setBoolean", new Class[]{int.class, boolean.class}, parameterIndex, x);
    }
    
    @Override
    public final void setByte(final int parameterIndex, final byte x) throws SQLException {
        setParameter(parameterIndex, "setByte", new Class[]{int.class, byte.class}, parameterIndex, x);
    }
    
    @Override
    public final void setShort(final int parameterIndex, final short x) throws SQLException {
        setParameter(parameterIndex, "setShort", new Class[]{int.class, short.class}, parameterIndex, x);
    }
    
    @Override
    public final void setInt(final int parameterIndex, final int x) throws SQLException {
        setParameter(parameterIndex, "setInt", new Class[]{int.class, int.class}, parameterIndex, x);
    }
    
    @Override
    public final void setLong(final int parameterIndex, final long x) throws SQLException {
        setParameter(parameterIndex, "setLong", new Class[]{int.class, long.class}, parameterIndex, x);
    }
    
    @Override
    public final void setFloat(final int parameterIndex, final float x) throws SQLException {
        setParameter(parameterIndex, "setFloat", new Class[]{int.class, float.class}, parameterIndex, x);
    }
    
    @Override
    public final void setDouble(final int parameterIndex, final double x) throws SQLException {
        setParameter(parameterIndex, "setDouble", new Class[]{int.class, double.class}, parameterIndex, x);
    }
    
    @Override
    public final void setString(final int parameterIndex, final String x) throws SQLException {
        setParameter(parameterIndex, "setString", new Class[]{int.class, String.class}, parameterIndex, x);
    }
    
    @Override
    public final void setBigDecimal(final int parameterIndex, final BigDecimal x) throws SQLException {
        setParameter(parameterIndex, "setBigDecimal", new Class[]{int.class, BigDecimal.class}, parameterIndex, x);
    }
    
    @Override
    public final void setDate(final int parameterIndex, final Date x) throws SQLException {
        setParameter(parameterIndex, "setDate", new Class[]{int.class, Date.class}, parameterIndex, x);
    }
    
    @Override
    public final void setDate(final int parameterIndex, final Date x, final Calendar cal) throws SQLException {
        setParameter(parameterIndex, "setDate", new Class[]{int.class, Date.class, Calendar.class}, parameterIndex, x, cal);
    }
    
    @Override
    public final void setTime(final int parameterIndex, final Time x) throws SQLException {
        setParameter(parameterIndex, "setTime", new Class[]{int.class, Time.class}, parameterIndex, x);
    }
    
    @Override
    public final void setTime(final int parameterIndex, final Time x, final Calendar cal) throws SQLException {
        setParameter(parameterIndex, "setTime", new Class[]{int.class, Time.class, Calendar.class}, parameterIndex, x, cal);
    }
    
    @Override
    public final void setTimestamp(final int parameterIndex, final Timestamp x) throws SQLException {
        setParameter(parameterIndex, "setTimestamp", new Class[]{int.class, Timestamp.class}, parameterIndex, x);
    }
    
    @Override
    public final void setTimestamp(final int parameterIndex, final Timestamp x, final Calendar cal) throws SQLException {
        setParameter(parameterIndex, "setTimestamp", new Class[]{int.class, Timestamp.class, Calendar.class}, parameterIndex, x, cal);
    }
    
    @Override
    public final void setBytes(final int parameterIndex, final byte[] x) throws SQLException {
        setParameter(parameterIndex, "setBytes", new Class[]{int.class, byte[].class}, parameterIndex, x);
    }
    
    @Override
    public final void setBlob(final int parameterIndex, final Blob x) throws SQLException {
        setParameter(parameterIndex, "setBlob", new Class[]{int.class, Blob.class}, parameterIndex, x);
    }
    
    @Override
    public final void setBlob(final int parameterIndex, final InputStream x) throws SQLException {
        setParameter(parameterIndex, "setBlob", new Class[]{int.class, InputStream.class}, parameterIndex, x);
    }
    
    @Override
    public final void setBlob(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        setParameter(parameterIndex, "setBlob", new Class[]{int.class, InputStream.class, long.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setClob(final int parameterIndex, final Clob x) throws SQLException {
        setParameter(parameterIndex, "setClob", new Class[]{int.class, Clob.class}, parameterIndex, x);
    }
    
    @Override
    public final void setClob(final int parameterIndex, final Reader x) throws SQLException {
        setParameter(parameterIndex, "setClob", new Class[]{int.class, Reader.class}, parameterIndex, x);
    }
    
    @Override
    public final void setClob(final int parameterIndex, final Reader x, final long length) throws SQLException {
        setParameter(parameterIndex, "setClob", new Class[]{int.class, Reader.class, long.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x) throws SQLException {
        setParameter(parameterIndex, "setAsciiStream", new Class[]{int.class, InputStream.class}, parameterIndex, x);
    }
    
    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        setParameter(parameterIndex, "setAsciiStream", new Class[]{int.class, InputStream.class, int.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setAsciiStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        setParameter(parameterIndex, "setAsciiStream", new Class[]{int.class, InputStream.class, long.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setUnicodeStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        setParameter(parameterIndex, "setUnicodeStream", new Class[]{int.class, InputStream.class, int.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x) throws SQLException {
        setParameter(parameterIndex, "setBinaryStream", new Class[]{int.class, InputStream.class}, parameterIndex, x);
    }
    
    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
        setParameter(parameterIndex, "setBinaryStream", new Class[]{int.class, InputStream.class, int.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setBinaryStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
        setParameter(parameterIndex, "setBinaryStream", new Class[]{int.class, InputStream.class, long.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x) throws SQLException {
        setParameter(parameterIndex, "setCharacterStream", new Class[]{int.class, Reader.class}, parameterIndex, x);
    }
    
    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x, final int length) throws SQLException {
        setParameter(parameterIndex, "setCharacterStream", new Class[]{int.class, Reader.class, int.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setCharacterStream(final int parameterIndex, final Reader x, final long length) throws SQLException {
        setParameter(parameterIndex, "setCharacterStream", new Class[]{int.class, Reader.class, long.class}, parameterIndex, x, length);
    }
    
    @Override
    public final void setURL(final int parameterIndex, final URL x) throws SQLException {
        setParameter(parameterIndex, "setURL", new Class[]{int.class, URL.class}, parameterIndex, x);
    }
    
    @Override
    public final void setSQLXML(final int parameterIndex, final SQLXML x) throws SQLException {
        setParameter(parameterIndex, "setSQLXML", new Class[]{int.class, SQLXML.class}, parameterIndex, x);
    }
    
    @Override
    public final void setRef(final int parameterIndex, final Ref x) throws SQLException {
        setParameter(parameterIndex, "setRef", new Class[]{int.class, Ref.class}, parameterIndex, x);
    }
    
    @Override
    public final void setObject(final int parameterIndex, final Object x) throws SQLException {
        setParameter(parameterIndex, "setObject", new Class[]{int.class, Object.class}, parameterIndex, x);
    }
    
    @Override
    public final void setObject(final int parameterIndex, final Object x, final int targetSqlType) throws SQLException {
        setParameter(parameterIndex, "setObject", new Class[]{int.class, Object.class, int.class}, parameterIndex,  x, targetSqlType);
    }
    
    @Override
    public final void setObject(final int parameterIndex, final Object x, final int targetSqlType, final int scaleOrLength) throws SQLException {
        setParameter(parameterIndex, "setObject", new Class[]{int.class, Object.class, int.class, int.class}, parameterIndex, x, targetSqlType, scaleOrLength);
    }
    
    @Override
    public final void clearParameters() throws SQLException {
        //parameters.clear();
    }
    
    private void setParameter(final int parameterIndex, final String methodName, final Class[] parameterTypes, final Object... arguments) {
        //parameters.recordMethodInvocation(parameterIndex, methodName, parameterTypes, arguments);
    }
}
