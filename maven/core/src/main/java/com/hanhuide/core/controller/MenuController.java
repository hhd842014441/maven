package com.hanhuide.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.core.controller
 * @ClassName: MenuController
 * @Author: 韩惠德
 * @Description: 菜单控制
 * @Date: 2020/1/15 12:58
 * @Version: 1.0
 */
@RestController
@RequestMapping("role")
public class MenuController {
    public static String mains(String a) {
        return a;
    }
}
