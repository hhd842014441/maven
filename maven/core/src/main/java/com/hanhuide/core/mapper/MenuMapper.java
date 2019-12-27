package com.hanhuide.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.core.model.SysMenu;
import com.hanhuide.core.model.SysRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/22
 * @Description：
 */
public interface MenuMapper extends BaseMapper{
    List<SysMenu> getAllMenuTree();

    List<SysMenu> getParentMenu(Long parentId);

    Integer update(Map<String, Object> map);

    Integer add(SysMenu sysMenu);

    List<String> getPerIdList(Long roleId);

    Integer addRP(SysRoleMenu sysRoleMenu);

    Integer del(Long roleId);

    Integer getCount(Long roleId);

    Integer delByPerid(Long menuId);
}
