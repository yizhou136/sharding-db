package com.zy.shardingdb.core.exception;

/**
 * @author by zy.
 */
public class ShardingJdbcException extends ShardingDBException{

    private static final long serialVersionUID = -1221829416939232281L;

    public ShardingJdbcException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public ShardingJdbcException(final String message, final Exception cause) {
        super(message, cause);
    }

    public ShardingJdbcException(final Exception cause){super(cause);};
}
