package com.hanhuide.security.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hanhuide.security.input.PageInput;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysUser;
import com.hanhuide.security.service.RoleService;
import com.hanhuide.security.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PostMapping("/list")
    public CustomResponseBody list(@RequestBody PageInput pageInput) {
        CustomResponseBody body = new CustomResponseBody();
        Page<SysUser> userPage = new Page<>(pageInput.getCurrent(), pageInput.getNum());//参数一是当前页，参数二是每页个数
        body.setResult(userService.findAll(userPage, null));
        return body;
    }

    @ApiOperation(value = "加载菜单树接口")
    @PostMapping("/menus")
    public Object menuButtonTree(String username) {
        CustomResponseBody body = new CustomResponseBody();
        body.setResult(userService.getMenuTree(username));
        return body;
    }
    @ApiOperation(value = "加载按钮权限接口")
    @PostMapping("/permissions")
    public Object findPermissions(String username) {
        CustomResponseBody body = new CustomResponseBody();
        body.setResult(userService.findPermissions(username));
        return body;
    }

}
