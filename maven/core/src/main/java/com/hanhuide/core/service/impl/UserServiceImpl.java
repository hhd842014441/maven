package com.hanhuide.core.service.impl;

import com.hanhuide.core.mapper.MenuMapper;
import com.hanhuide.core.mapper.RoleMapper;
import com.hanhuide.core.mapper.UserMapper;
import com.hanhuide.core.model.CustomResponseBody;
import com.hanhuide.core.model.SysMenu;
import com.hanhuide.core.model.SysUser;
import com.hanhuide.core.service.UserService;
import com.hanhuide.toolkit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: maven
 * @description: 用户管理
 * @author: 韩惠德
 * @create: 2019-12-23 15:49
 * @version: 1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUser findByUsername(String username) {
        SysUser user = userMapper.selectByUserName(username);
        log.info("用户信息:{}", user);
        return user;
    }

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    @Override
    public CustomResponseBody login(String username, String password) {
        return null;
    }

    /**
     * 获取用户信息，包括角色列表，权限资源，返回给前端使用
     *
     * @param username
     * @return
     */
    @Override
    public CustomResponseBody getUserInfo(String username) {
        return null;
    }

    /**
     * 获取菜单树
     *
     * @param username
     * @return
     */
    @Override
    public CustomResponseBody getMenuTree(String username) {
        return null;
    }

    /**
     * 获取所有的菜单树树形表格用
     *
     * @param sysMenus
     * @return
     */
    @Override
    public Object getAllMenuTree(List<SysMenu> sysMenus) {
        List<Map<String, Object>> permissions = new ArrayList<>();
        sysMenus.forEach(menu -> {
            if (menu != null) {
                List<SysMenu> sysMenuList = menuMapper.getParentMenu(menu.getMenuId());
                Map<String, Object> map1 = new HashMap<>();
                map1.put("id", menu.getMenuId());
                map1.put("label", menu.getMenuName());
                if (sysMenuList != null && sysMenuList.size() > 0) {
                    map1.put("children", getAllMenuTree(sysMenuList));
                }
                permissions.add(map1);
            }
        });
        return permissions;
    }

    /**
     * 通过菜单id获取菜单树
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysMenu> getMenuTreeByPid(Long parentId) {
        return menuMapper.getParentMenu(parentId);
    }

}
