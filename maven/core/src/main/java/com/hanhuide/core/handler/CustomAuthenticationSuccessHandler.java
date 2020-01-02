package com.hanhuide.core.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.CustomResponseBody;
import com.hanhuide.core.model.CustomAuthDetails;
import com.hanhuide.core.utils.AccessAddressUtil;
import com.hanhuide.core.utils.JwtTokenUtil;
import com.hanhuide.core.utils.TokenRedisUtil;
import com.hanhuide.toolkit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: maven
 * @description: 用户登录成功时返回给前端的数据
 * @author: 韩惠德
 * @create: 2019-11-29 13:53
 * @version: 1.0
 **/
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenRedisUtil redisUtil;
    @Value("${jwt.header}")
    private String header;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String ip = AccessAddressUtil.getIpAddress(httpServletRequest);
        CustomResponseBody responseBody = new CustomResponseBody();
        CustomAuthDetails userDetails = (CustomAuthDetails) authentication.getDetails();
        log.info("用户登录信息{}", userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("ip", ip);
        String jwtToken = jwtTokenUtil.doGenerateToken(map, userDetails.getUsername());
        response.setHeader(header, jwtToken);
        responseBody.setJwtToken(jwtToken);
        responseBody.setStatus(ResultEnum.USER_LOGIN_SUCCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_LOGIN_SUCCESS.getMessage());
        String currentIp = AccessAddressUtil.getIpAddress(httpServletRequest);
        redisUtil.setTokenRefresh(jwtToken, userDetails.getUsername(), currentIp);
        log.info("保存到redis 中");
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}

