package com.hanhuide.core.handler;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.AjaxResponseBody;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: maven
 * @description: 用户登录失败时返回给前端的数据
 * @author: 韩惠德
 * @create: 2019-11-29 13:52
 * @version: 1.0
 **/
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody responseBody = new AjaxResponseBody();
        if (exception instanceof UsernameNotFoundException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("用户不存在!");
        } else if (exception instanceof BadCredentialsException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("用户名或密码错误！");
        } else if (exception instanceof LockedException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("用户已被锁定！");
        } else if (exception instanceof DisabledException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("用户不可用！,验证码错误");
        } else if (exception instanceof AccountExpiredException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("账户已过期！");
        } else if (exception instanceof CredentialsExpiredException) {
            ResultEnum.USER_LOGIN_FAILED.setMessage("用户密码已过期！");
//        } else if(exception instanceof TokenInvalidException) {
//            ResultEnum.USER_LOGIN_FAILED .setMessage( "登陆信息已过期,请重新登陆";
        } else {
            ResultEnum.USER_LOGIN_FAILED.setMessage("认证失败，请联系网站管理员！");
        }
        responseBody.setStatus(ResultEnum.USER_LOGIN_FAILED.getCode());
        responseBody.setMsg(ResultEnum.USER_LOGIN_FAILED.getMessage());
        httpServletResponse.getWriter().write(responseBody.toString());
    }
}
