package com.hanhuide.core.handler;

import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.CustomResponseBody;
import com.hanhuide.core.model.CustomAuthDetails;
import com.hanhuide.core.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        CustomResponseBody responseBody = new CustomResponseBody();
        CustomAuthDetails userDetails = (CustomAuthDetails) authentication.getDetails();
        log.info("用户登录信息{}", userDetails);
        Map<String, Object> map = new HashMap<>();
        String jwtToken = jwtTokenUtil.doGenerateToken(map, userDetails.getUsername());
        responseBody.setJwtToken(jwtToken);
        responseBody.setStatus(ResultEnum.USER_LOGIN_SUCCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_LOGIN_SUCCESS.getMessage());
        response.getWriter().write(responseBody.toString());
    }
}

