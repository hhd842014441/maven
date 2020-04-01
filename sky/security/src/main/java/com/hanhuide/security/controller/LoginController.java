package com.hanhuide.security.controller;

import com.hanhuide.security.model.CustomAuthDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String showLogin() {
        return "login.html";
    }

}
