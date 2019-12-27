package com.hanhuide.security.controller;

import com.hanhuide.security.model.AjaxResponseBody;
import com.hanhuide.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/4
 * @Description：
 */
@RestController
public class AuthController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public AjaxResponseBody login(@RequestBody Map<String, Object> map) {
        String username = map.get("username").toString();
        String passwordAES = map.get("password").toString();
        return userService.login(username, passwordAES);
    }
}
