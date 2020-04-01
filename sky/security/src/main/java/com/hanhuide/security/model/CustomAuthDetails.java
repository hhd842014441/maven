package com.hanhuide.security.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: maven
 * @description: 自定义用户登录时携带的额外信息
 * @author: 韩惠德
 * @create: 2019-12-30 14:36
 * @version: 1.0
 **/
@Getter
@Slf4j
@ToString
public class CustomAuthDetails extends WebAuthenticationDetails {
    private final String verifyCode;
    private final String username;
    private final String password;
    private final String captcha;

    public CustomAuthDetails(HttpServletRequest request) {
        super(request);
        this.verifyCode = request.getParameter("validateCodeText");
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
        this.captcha = request.getParameter("captcha");
    }

}
