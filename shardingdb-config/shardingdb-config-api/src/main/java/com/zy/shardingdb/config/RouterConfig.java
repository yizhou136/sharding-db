package com.zy.shardingdb.config;

/**
 * Created by Administrator on 2017/3/29.
 */
public class RouterConfig {
    private String name;
    private String shardingColumns;
    private String algorithmClass;
    private String algorithmExpression;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShardingColumns() {
        return shardingColumns;
    }

    public void setShardingColumns(String shardingColumns) {
        this.shardingColumns = shardingColumns;
    }

    public String getAlgorithmClass() {
        return algorithmClass;
    }

    public void setAlgorithmClass(String algorithmClass) {
        this.algorithmClass = algorithmClass;
    }

    public String getAlgorithmExpression() {
        return algorithmExpression;
    }

    public void setAlgorithmExpression(String algorithmExpression) {
        this.algorithmExpression = algorithmExpression;
    }
}
