package com.hanhuide.driver.logger;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.time.LocalDateTime;

/**
 * @program: maven
 * @description:配置p6spy输出日志
 * @author: 韩惠德
 * @create: 2019-12-24 15:03
 * @version: 1.0
 **/
public class P6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String s4) {
        return !"".equals(sql.trim()) ? "[ " + LocalDateTime.now() + " ] --- | took "
                + elapsed + "ms | " + category + " | connection " + connectionId + "\n "
                + sql + ";" : "";
    }
}
