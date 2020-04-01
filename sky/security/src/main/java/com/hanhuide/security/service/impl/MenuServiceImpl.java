package com.hanhuide.security.service.impl;

import com.hanhuide.security.mapper.MenuMapper;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysMenu;
import com.hanhuide.security.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    private MenuMapper menuMapper;
    /**
     * 更新菜单
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody update(Map<String, Object> map) {
        return null;
    }
    /**
     * 添加菜单
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody add(Map<String, Object> map) {
        return null;
    }
    /**
     * 获取所有菜单树，树形表格数据
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody queryAllMenusTree(Map<String, Object> map) {
        return null;
    }

    /**
     * 根据角色获取菜单集合
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody getPerIdList(Map<String, Object> map) {
        return null;
    }

    /**
     * 添加角色和菜单的对应关系
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody addRP(Map<String, Object> map) {
        return null;
    }

    /**
     * 通过菜单id删除menu
     * @param map
     * @return
     */
    @Override
    public CustomResponseBody del(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<SysMenu> allMenu() {
        return menuMapper.getAllMenuTree();
    }
}
