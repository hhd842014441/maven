package com.hanhuide.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.hanhuide.driver.config.DynamicDataSourceConfig;
@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.hanhuide")
@MapperScan(basePackages = "com.hanhuide.**.mapper")
@EnableSwagger2
@ComponentScan(basePackages = "com.hanhuide.*.*")
@EnableEurekaServer
//@EnableEurekaClient
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
