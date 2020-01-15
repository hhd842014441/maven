package com.hanhuide.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hanhuide.core.model.CustomResponseBody;
import com.hanhuide.core.model.SysMenu;
import com.hanhuide.core.model.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @Author:YangWenbin
 * @Description：
 * @Date:20:44 2019/1/5
 * @ModifiedBy:
 */

public interface UserService {

    SysUser findByUsername(String username);

    CustomResponseBody login(String username, String password);

    CustomResponseBody getUserInfo(String username);

    /**
     * 获取菜单树
     *
     * @param username
     * @return
     */

    Set<SysMenu> getMenuTree(String username);

    Object getAllMenuTree(List<SysMenu> sysMenus);

    List<SysMenu> getMenuTreeByPid(Long parentId);

    List<SysUser> findAll(Page<SysUser> userPage, Object o);
}
