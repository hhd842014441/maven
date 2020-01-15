package com.hanhuide.driver.dataSource;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.driver.dataSource
 * @ClassName: TransactionNames
 * @Author: 韩惠德
 * @Description: 多个事务管理
 * @Date: 2020/1/15 15:41
 * @Version: 1.0
 */
public interface TransactionNames {
    String MASTER = "transactionA";
    String CLUSTER = "transactionB";
}
