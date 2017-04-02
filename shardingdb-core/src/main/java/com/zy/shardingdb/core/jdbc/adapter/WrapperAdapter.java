package com.zy.shardingdb.core.jdbc.adapter;

import com.google.common.base.Preconditions;
import com.sun.istack.internal.Nullable;
import com.zy.shardingdb.core.exception.ShardingJdbcException;
import com.zy.shardingdb.core.utils.MethodInvocation;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.*;
import java.util.stream.Stream;


/**
 *
 * @author by zy.
 */
public abstract class WrapperAdapter implements Wrapper {

    private final Map<String,Method> methodInvocationsMap = new HashMap<>();
    private final Collection<MethodInvocation> methodInvocations = new ArrayList<>(8);

    @SuppressWarnings("unchecked")
    @Override
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return (T) this;
        }
        throw new SQLException(String.format("[%s] cannot be unwrapped as [%s]", getClass().getName(), iface.getName()));
    }
    
    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }

    protected abstract boolean cantInvokedNow();

    protected final void detectConditionMethod(Class clazz){
        Stream.of(clazz.getDeclaredMethods())
                .forEach((m)->{
                    String methodSignature = genMethodSignature(m);
                    methodInvocationsMap.put(methodSignature, m);
                });
    }

    public static final String genMethodSignature(String methodName, @Nullable Class... classes){
        StringJoiner stringJoiner = new StringJoiner("_").add(methodName);
        if (Objects.nonNull(classes))
            Stream.of(classes).forEach((clazz)->{
                stringJoiner.add(clazz.getName());
            });
        return stringJoiner.toString();
    }

    public static final String genMethodSignature(Method method){
        Preconditions.checkNotNull(method);
        return genMethodSignature(method.getName(), method.getParameterTypes());
    }

    protected final void recordMethodInvocation(final String methodName, final Class<?>[] argumentTypes, final Object[] arguments) {
        String methodSignature = genMethodSignature(methodName, argumentTypes);
        Method method = methodInvocationsMap.get(methodSignature);
        if (Objects.isNull(method)){
            throw new ShardingJdbcException("the [%s] method cant be found in methodInvocationsMap", methodName);
        }
        methodInvocations.add(new MethodInvocation(method, arguments));
    }

    protected final void replayMethodsInvocation(final Object target) {
        for (MethodInvocation each : methodInvocations) {
            each.invoke(target);
        }
    }
}
