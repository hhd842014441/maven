package com.hanhuide.security.service.impl;

import com.hanhuide.security.mapper.RoleMapper;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysRole;
import com.hanhuide.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: maven
 * @description: 角色处理
 * @author: 韩惠德
 * @create: 2019-12-23 15:48
 * @version: 1.0
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取角色列表
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody getRoleListByCond(Map<String, Object> map) {
        return null;
    }

    /**
     * 得到角色列表，资源管理使用
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody getAllRoleList(Map<String, Object> map) {
        return null;
    }

    /**
     * 通过资源id获取角色集合
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody getRoleListByPerId(Map<String, Object> map) {
        return null;
    }

    @Override
    public CustomResponseBody selectByUserName(Map<String, Object> map) {
        return null;
    }

    @Override
    public SysRole selectByRoleName(String roleName) {
        SysRole role = roleMapper.selectByRoleName(roleName);
        log.info("用户信息:{}", role);
        return role;
    }

    @Override
    public List<SysRole> selectBysRole() {
        List<SysRole> roles = roleMapper.getAllRoleList();
        return roles;
    }


    /**
     * 添加角色
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody addRoleById(Map<String, Object> map) {
        return null;
    }

    /**
     * 删除角色
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody delRoleById(Map<String, Object> map) {
        return null;
    }

    /**
     * 更新角色信息
     *
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody updateById(Map<String, Object> map) {
        return null;
    }
}
