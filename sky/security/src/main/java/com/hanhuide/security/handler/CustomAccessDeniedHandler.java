package com.hanhuide.security.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.security.enums.ResultEnum;
import com.hanhuide.security.model.CustomResponseBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven
 * @description: 用户无权登录
 * @author: 韩惠德
 * @create: 2019-11-29 13:49
 * @version: 1.0
 **/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        CustomResponseBody responseBody = new CustomResponseBody();
        responseBody.setStatus(ResultEnum.USER_NO_ACCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_NO_ACCESS.getMessage());
        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
