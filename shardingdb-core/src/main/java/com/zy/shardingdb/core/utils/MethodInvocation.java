package com.zy.shardingdb.core.utils;

import com.zy.shardingdb.core.exception.ShardingDBException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author by zy.
 */
public final class MethodInvocation {

    private final Method method;
    private final Object[] arguments;

    public MethodInvocation(Method method, Object[] arguments) {
        this.method = method;
        this.arguments = arguments;
    }

    public void invoke(final Object target) {
        try {
            method.invoke(target, arguments);
        } catch (final IllegalAccessException | InvocationTargetException ex) {
            throw new ShardingDBException("Invoke jdbc method exception", ex);
        }
    }
}
