package com.hanhuide.security.handler;

import com.hanhuide.security.model.CustomAuthDetails;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: maven
 * @description: 用于在Spring Security登录过程中对用户的登录信息的详细信息进行填充
 * @author: 韩惠德
 * @create: 2019-12-30 14:41
 * @version: 1.0
 **/
@Component("authenticationDetailsSource")
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, CustomAuthDetails> {
    @Override
    public CustomAuthDetails buildDetails(HttpServletRequest request) {
        return new CustomAuthDetails(request);
    }
}
