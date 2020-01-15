package com.hanhuide.core.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hanhuide.core.input.PageInput;
import com.hanhuide.core.model.SysUser;
import com.hanhuide.core.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.core.controller
 * @ClassName: UserController
 * @Author: 韩惠德
 * @Description: 用户管理
 * @Date: 2020/1/15 14:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PostMapping("/list")
    public List<SysUser> list(@RequestBody PageInput pageInput) {
        Page<SysUser> userPage = new Page<>(pageInput.getCurrent(), pageInput.getNum());//参数一是当前页，参数二是每页个数
        return userService.findAll(userPage, null);
    }


}
