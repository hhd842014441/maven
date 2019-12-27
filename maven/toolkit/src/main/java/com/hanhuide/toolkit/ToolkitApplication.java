package com.hanhuide.toolkit;

import com.hanhuide.toolkit.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ToolkitApplication {

    public static void main(String[] args) {

        SpringApplication.run(ToolkitApplication.class, args);
    }

}
