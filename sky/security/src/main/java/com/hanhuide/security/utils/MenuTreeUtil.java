package com.hanhuide.security.utils;

import com.hanhuide.security.model.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.core.utils
 * @ClassName: MenuTreeUtil
 * @Author: 韩惠德
 * @Description: 动态菜单路由
 * @Date: 2020/1/23 22:47
 * @Version: 1.0
 */
@Slf4j
@Component
public class MenuTreeUtil {
    /**
     * 递归根节点
     *
     * @param nodes
     * @return
     */
    public Set<SysMenu> genRoot(Set<SysMenu> nodes) {
        Set<SysMenu> root = new HashSet<>();
        //遍历数据
        nodes.forEach(menu -> {
            //当父id是0的时候应该是根节点
            if (menu.getParentId() == 0) {
                root.add(menu);
            }
        });
        //这里是子节点的创建方法
        root.forEach(menu -> {
            genChildren(menu, nodes);
        });
        //返回数据
        return root;
    }

    /**
     * 递归子节点
     *
     * @param menu
     * @param nodes
     * @return
     */
    private SysMenu genChildren(SysMenu menu, Set<SysMenu> nodes) {
        //遍历传过来的数据
        for (SysMenu menu1 : nodes) {
            //如果数据中的父id和上面的per_id一致应该就放children中去
            if (menu.getMenuId().equals(menu1.getParentId())) {
                //如果当前节点的子节点是空的则初始化，如果不为空就加进去
                if (menu.getMenuChild() == null) {
                    menu.setMenuChild(new ArrayList<SysMenu>());
                }
                menu.getMenuChild().add(genChildren(menu1, nodes));
            }
        }
        //返回数据
        return menu;
    }
}
