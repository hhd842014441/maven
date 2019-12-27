package com.hanhuide.core.handler;

import com.alibaba.fastjson.JSON;
import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.AjaxResponseBody;
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
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        responseBody.setResultEnum(ResultEnum.USER_NEED_AUTHORITIES);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
