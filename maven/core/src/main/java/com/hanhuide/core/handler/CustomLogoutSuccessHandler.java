package com.hanhuide.core.handler;

import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.CustomResponseBody;
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
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        CustomResponseBody responseBody = new CustomResponseBody();
        responseBody.setStatus(ResultEnum.USER_LOGOUT_SUCCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_LOGOUT_SUCCESS.getMessage());
        response.getWriter().write(responseBody.toString());
    }
}
