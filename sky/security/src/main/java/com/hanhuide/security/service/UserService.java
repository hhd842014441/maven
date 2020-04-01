package com.hanhuide.security.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysMenu;
import com.hanhuide.security.model.SysUser;

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


    List<SysMenu> getMenuTreeByPid(Long parentId);

    List<SysUser> findAll(Page<SysUser> userPage, Object o);

    Set<SysMenu> findPermissions(String username);
}
