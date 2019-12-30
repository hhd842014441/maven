package com.hanhuide.core.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.AjaxResponseBody;
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
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setStatus(ResultEnum.USER_NO_ACCESS.getCode());
        responseBody.setMsg(ResultEnum.USER_NO_ACCESS.getMessage());
        httpServletResponse.getWriter().write(responseBody.toString());
    }
}
