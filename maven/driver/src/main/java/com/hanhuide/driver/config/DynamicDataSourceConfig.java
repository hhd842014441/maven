package com.hanhuide.driver.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.hanhuide.driver.dataSource.DataSourceNames;
import com.hanhuide.driver.dataSource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源 ，将数据源添加到系统中
 *
 * @author 韩惠德
 * @date 2018-05-14
 */
@Slf4j
@Configuration
public class DynamicDataSourceConfig {

    /**
     * 创建 DataSource Bean
     */

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource oneDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.cluster")
    public DataSource twoDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    /**
     * 如果还有数据源,在这继续添加 DataSource Bean
     */

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MASTER, oneDataSource);
        targetDataSources.put(DataSourceNames.CLUSTER, twoDataSource);
        // 还有数据源,在targetDataSources中继续添加
        log.info("DataSources:" + targetDataSources);
        return new DynamicDataSource(oneDataSource, targetDataSources);
    }
}
