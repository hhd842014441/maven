package com.hanhuide.driver.annotation;


import com.hanhuide.driver.dataSource.DataSourceNames;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author 韩惠德
 * @date 2018-05-14
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default DataSourceNames.MASTER;
}
