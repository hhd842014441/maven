package com.hanhuide.driver;

import com.hanhuide.driver.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 动态数据源配置,需要将自有的配置依赖(DynamicDataSourceConfig),将原有的依赖去除(DataSourceAutoConfiguration)
 *
 * @author 韩惠德
 * @date 2018-05-15
 */
@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.hanhuide.*.mapper")
@EnableSwagger2
public class DriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class, args);
    }
}
