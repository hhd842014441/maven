package com.hanhuide.security.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.security.enums.ResultEnum;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.utils.TokenRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven
 * @description: 处理退出成功
 * @author: 韩惠德
 * @create: 2019-11-29 13:55
 * @version: 1.0
 **/
@Component
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Value("${jwt.startsWith}")
    private String startsWith;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private TokenRedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String authHeader = httpServletRequest.getHeader(tokenHeader);
        System.out.println(authHeader);
        if (authHeader != null && authHeader.startsWith(startsWith)) {
            final String authToken = authHeader.substring(startsWith.length());
            System.out.println(authToken);
            //将token放入黑名单中
            redisUtil.hset(authToken);
            log.info("token：{}已加入redis黑名单", authToken);
        }
        CustomResponseBody responseBody = new CustomResponseBody();
        responseBody.setStatus(ResultEnum.USER_LOGOUT_SUCCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_LOGOUT_SUCCESS.getMessage());
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
