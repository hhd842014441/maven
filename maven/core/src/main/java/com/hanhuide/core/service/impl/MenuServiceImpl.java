package com.hanhuide.core.service.impl;

import com.hanhuide.core.model.AjaxResponseBody;
import com.hanhuide.core.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: maven
 * @description: 权限菜单按钮
 * @author: 韩惠德
 * @create: 2019-12-23 15:46
 * @version: 1.0
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    /**
     * 更新菜单
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody update(Map<String, Object> map) {
        return null;
    }
    /**
     * 添加菜单
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody add(Map<String, Object> map) {
        return null;
    }
    /**
     * 获取所有菜单树，树形表格数据
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody queryAllMenusTree(Map<String, Object> map) {
        return null;
    }

    /**
     * 根据角色获取菜单集合
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody getPerIdList(Map<String, Object> map) {
        return null;
    }

    /**
     * 添加角色和菜单的对应关系
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody addRP(Map<String, Object> map) {
        return null;
    }

    /**
     * 通过菜单id删除menu
     * @param map
     * @return
     */
    @Override
    public AjaxResponseBody del(Map<String, Object> map) {
        return null;
    }
}
