package com.hanhuide.security.utils;

import com.hanhuide.toolkit.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-20 16:31
 * @version: 1.0
 **/
public class TokenRedisUtil {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${jwt.expiration}")
    private int expirationSeconds;
    @Value("${jwt.token.validTime}")
    private int validTime;

    /**
     * 将token加入到redis黑名单中
     *
     * @param token
     */
    public void addBlackList(String token) {
        redisUtil.hset("blacklist", token, "true");
    }

    /**
     * 判断此token是否在黑名单中
     *
     * @param token
     * @return
     */
    public Boolean isBlackList(String token) {
        return redisUtil.hasKey("blacklist", token);
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getTokenValidTimeByToken(String token) {
        return redisTemplate.opsForHash().get(token, "tokenValidTime");
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getUsernameByToken(String token) {
        return redisTemplate.opsForHash().get(token, "username");
    }

    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getIPByToken(String token) {
        return redisTemplate.opsForHash().get(token, "ip");
    }

    /**
     * 查询token下的过期时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getExpirationTimeByToken(String token) {
        return redisTemplate.opsForHash().get(token, "expirationTime");
    }

    public void setTokenRefresh(String token, String username, String ip) {
        //刷新时间
        Integer expire = validTime * 24 * 60 * 60 * 1000;

        redisUtil.hset(token, "tokenValidTime", DateUtil.getAddDayTime(validTime), expire);
        redisUtil.hset(token, "expirationTime", DateUtil.getAddDaySecond(expirationSeconds), expire);
        redisUtil.hset(token, "username", username, expire);
        redisUtil.hset(token, "ip", ip, expire);
    }
}
