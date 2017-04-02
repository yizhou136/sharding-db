package com.zy.shardingdb.core.exception;

/**
 * @author by zy.
 */
public class ShardingParserException extends ShardingJdbcException{
    private static final long serialVersionUID = -1341823416939232211L;

    public ShardingParserException(String errorMessage, Object... args) {
        super(errorMessage, args);
    }
}
