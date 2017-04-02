package com.zy.shardingdb.core.exception;

/**
 * @author by zy.
 */
public class ShardingDBException extends RuntimeException{

    private static final long serialVersionUID = -1221829416939232282L;

    public ShardingDBException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public ShardingDBException(final String message, final Exception cause) {
        super(message, cause);
    }

    public ShardingDBException(final Exception cause) {
        super(cause);
    }
}
