package com.hanhuide.security.service;

import com.hanhuide.security.model.AjaxResponseBody;
import com.hanhuide.security.model.SysMenu;
import com.hanhuide.security.model.SysUser;

import java.util.List;

/**
 * @Author:YangWenbin
 * @Description：
 * @Date:20:44 2019/1/5
 * @ModifiedBy:
 */

public interface UserService {


    SysUser findByUsername(String username);


    AjaxResponseBody login(String username, String password);

    AjaxResponseBody getUserInfo(String username);

    /**
     * 获取菜单树
     *
     * @param username
     * @return
     */
    AjaxResponseBody getMenuTree(String username);

    Object getAllMenuTree(List<SysMenu> sysMenus);

    List<SysMenu> getMenuTreeByPid(Long parentId);


}
