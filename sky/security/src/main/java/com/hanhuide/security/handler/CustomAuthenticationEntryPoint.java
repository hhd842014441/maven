package com.hanhuide.security.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.security.enums.ResultEnum;
import com.hanhuide.security.model.CustomResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven
 * @description:用户未登录时返回给前端的数据
 * @author: 韩惠德
 * @create: 2019-11-29 13:46
 * @version: 1.0
 **/
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        CustomResponseBody responseBody = new CustomResponseBody();
        responseBody.setStatus(ResultEnum.USER_NEED_AUTHORITIES.getCode());
        responseBody.setMsg(ResultEnum.USER_NEED_AUTHORITIES.getMessage());
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
