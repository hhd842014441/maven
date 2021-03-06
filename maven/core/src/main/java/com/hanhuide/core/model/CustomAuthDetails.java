package com.hanhuide.core.model;

import com.google.code.kaptcha.Constants;
import com.hanhuide.core.utils.CookieUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: maven
 * @description: 自定义用户登录时携带的额外信息
 * @author: 韩惠德
 * @create: 2019-12-30 14:36
 * @version: 1.0
 **/
@Data
@Slf4j
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

    @Override
    public String toString() {
        return "CustomAuthDetails{" +
                "verifyCode='" + verifyCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
