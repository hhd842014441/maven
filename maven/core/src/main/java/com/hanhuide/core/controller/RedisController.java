package com.hanhuide.core.controller;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-25 13:21
 * @version: 1.0
 **/

import com.hanhuide.toolkit.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: maven
 * @description: 测试redis集群
 * @author: 韩惠德
 * @create: 2019-11-29 15:49
 * @version: 1.0
 **/
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("index")
    public Map<String, Object> ceshiRedis() {
        redisUtil.set("测试2", "dlfdsjfldsjfldsjfldsjfldsjflsdjf");
        return null;
    }
}
