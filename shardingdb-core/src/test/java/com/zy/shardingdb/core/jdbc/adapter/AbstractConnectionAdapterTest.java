package com.zy.shardingdb.core.jdbc.adapter;

import com.zy.shardingdb.core.jdbc.ShardingConnection;
import com.zy.shardingdb.core.jdbc.unsupported.AbstractUnsupportedOperationConnection;
import org.junit.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * @author by zy.
 */
public class AbstractConnectionAdapterTest {
    public final static long totoalSizeMax = 2L * 1024 * 1024 * 1024;

    @Test
    public void testReceiveMethods(){
        Method methodes[] = AbstractConnectionAdapter.class.getMethods();
        Method declaredMethodes[] = AbstractConnectionAdapter.class.getDeclaredMethods();


        Method parentDeclaredMethodes[] = AbstractUnsupportedOperationConnection.class.getDeclaredMethods();

        Stream.of(declaredMethodes).forEach((m)->{
            //System.out.println(WrapperAdapter.genMethodSignature(m));
            System.out.println("types:"+m.getParameterTypes());
            System.out.println(WrapperAdapter.genMethodSignature(m.getName(), m.getParameterTypes()));

            System.out.println(WrapperAdapter.genMethodSignature(m.getName(), null));
        });

        ShardingConnection shardingConnection = new ShardingConnection();
        try {
            shardingConnection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
