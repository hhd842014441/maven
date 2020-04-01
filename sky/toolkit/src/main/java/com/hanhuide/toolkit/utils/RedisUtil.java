package com.hanhuide.toolkit.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: maven
 * @description: redis工具类
 * @author: 韩惠德
 * @create: 2019-11-29 14:23
 * @version: 1.0
 **/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;
    public RedisUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查询key,支持模糊查询
     *
     * @param key 传过来时key的前后端已经加入了*，或者根据具体处理
     */
    public Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 字符串获取值
     *
     * @param key
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 字符串存入值
     * 默认过期时间为2小时
     *
     * @param key
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 7200, TimeUnit.SECONDS);
    }

    /**
     * 字符串存入值
     *
     * @param expire 过期时间（毫秒计）
     * @param key
     */
    public void set(String key, String value, Integer expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 删出key
     * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 添加单个
     * 默认过期时间为两小时
     *
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     */
    public void hset(String key, String filed, Object domain) {
        redisTemplate.opsForHash().put(key, filed, domain);
    }

    /**
     * 添加单个
     *
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     * @param expire 过期时间（毫秒计）
     */
    public void hset(String key, String filed, Object domain, Integer expire) {
        redisTemplate.opsForHash().put(key, filed, domain);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 添加HashMap
     *
     * @param key key
     * @param hm  要存入的hash表
     */
    public void hset(String key, HashMap<String, Object> hm) {
        redisTemplate.opsForHash().putAll(key, hm);
    }

    /**
     * 如果key存在就不覆盖
     *
     * @param key
     * @param filed
     * @param domain
     */
    public void hsetAbsent(String key, String filed, Object domain) {
        redisTemplate.opsForHash().putIfAbsent(key, filed, domain);
    }

    /**
     * 查询key和field所确定的值
     *
     * @param key   查询的key
     * @param field 查询的field
     * @return HV
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 查询该key下所有值
     *
     * @param key 查询的key
     * @return Map<HK, HV>
     */
    public Object hget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
    public void deleteKey(String key) {
        redisTemplate.opsForHash().getOperations().delete(key);
    }

    /**
     * 判断key和field下是否有值
     *
     * @param key   判断的key
     * @param field 判断的field
     */
    public Boolean hasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
    public Boolean hasKey(String key) {
        return redisTemplate.opsForHash().getOperations().hasKey(key);
    }


}
