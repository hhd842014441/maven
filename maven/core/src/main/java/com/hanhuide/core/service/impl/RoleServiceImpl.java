package com.hanhuide.core.service.impl;

import com.hanhuide.core.model.AjaxResponseBody;
import com.hanhuide.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    /**
     * 获取角色列表
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody getRoleListByCond(Map<String, Object> map) {
        return null;
    }

    /**
     * 得到角色列表，资源管理使用
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody getAllRoleList(Map<String, Object> map) {
        return null;
    }

    /**
     * 通过资源id获取角色集合
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody getRoleListByPerId(Map<String, Object> map) {
        return null;
    }

    @Override
    public AjaxResponseBody selectByUserName(Map<String, Object> map) {
        return null;
    }

    /**
     * 添加角色
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody addRoleById(Map<String, Object> map) {
        return null;
    }
    /**
     * 删除角色
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody delRoleById(Map<String, Object> map) {
        return null;
    }
    /**
     * 更新角色信息
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody updateById(Map<String, Object> map) {
        return null;
    }
}
