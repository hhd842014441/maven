package com.hanhuide.driver.config;

import com.hanhuide.driver.dataSource.DataSourceNames;
import com.hanhuide.driver.dataSource.TransactionNames;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.driver.config
 * @ClassName: TransactionConfig
 * @Author: 韩惠德
 * @Description: 多数据源多事务管理器
 * @Date: 2020/1/15 14:59
 * @Version: 1.0
 */
@Configuration
public class TransactionConfig {
    @Bean(name = TransactionNames.MASTER)
    public DataSourceTransactionManager transaction(@Qualifier(DataSourceNames.MASTER) DataSource firstDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(firstDataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name = TransactionNames.CLUSTER)
    public DataSourceTransactionManager transactionCLUSTER(@Qualifier(DataSourceNames.CLUSTER) DataSource firstDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(firstDataSource);
        return dataSourceTransactionManager;
    }
}
