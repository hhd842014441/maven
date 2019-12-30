package com.hanhuide.core.model;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: maven
 * @description: 自定义用户登录时携带的额外信息
 * @author: 韩惠德
 * @create: 2019-12-30 14:36
 * @version: 1.0
 **/
public class CustomerWebAuthenticationDetails  extends WebAuthenticationDetails {
    private String verifyCode;
    public CustomerWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode = request.getParameter("validateCodeText");
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    @Override
    public String toString() {
        return "CustomerWebAuthenticationDetails{" +
                "verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
