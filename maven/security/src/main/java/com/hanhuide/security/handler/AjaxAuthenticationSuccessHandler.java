package com.hanhuide.security.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.security.enums.ResultEnum;
import com.hanhuide.security.model.AjaxResponseBody;
import com.hanhuide.security.model.SelfUserDetails;
import com.hanhuide.security.utils.JwtTokenUtil;
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
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setResultEnum(ResultEnum.USER_LOGIN_SUCCESS);
        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
        log.info("用户登录信息{}", userDetails);
        Map<String, Object> map = new HashMap<>();
        String jwtToken = jwtTokenUtil.doGenerateToken(map, userDetails.getUsername());
        responseBody.setJwtToken(jwtToken);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}

