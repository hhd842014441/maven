package com.hanhuide.core.controller;

import com.hanhuide.core.model.CustomAuthDetails;
import com.hanhuide.core.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-27 14:06
 * @version: 1.0
 **/
@Slf4j
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String showLogin(CustomAuthDetails customAuthDetails) {
        return "login.html";
    }

}
