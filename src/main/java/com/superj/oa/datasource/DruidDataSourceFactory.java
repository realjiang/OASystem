package com.superj.oa.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    //DruidDataSource 阿里提供了连接信息等
    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }

    /**
     * 重写getDataSource（不同的连接池要求不同，不一定都要重写该方法）
     * @return DataSource接口 -》 连接池具体实现
     */
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();//初始化Druid数据源
        } catch (SQLException e) {
            throw new RuntimeException(e);//如果报错 抛出运行时异常
        }
        return this.dataSource;//返回连接池具体实现
    }
}
