package com.zy.shardingdb.config.file;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author by zy.
 */
public class ConnectionProxy implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method:"+method+" args:"+args);
        return null;
    }


    public static void main(String argv[]){
        Connection connection = (Connection) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Connection.class},
                new ConnectionProxy());

        try {
            connection.prepareCall("select * from user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
