package com.hanhuide.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.core.model.SysMenu;
import com.hanhuide.core.model.SysRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/7
 * @Description：
 */
public interface RoleMapper extends BaseMapper {

    Integer add(SysRole sysRole);

    Integer del(Long roleId);

    Integer update(SysRole sysRole);

    Set<Object> selectByUserName(String username);

    ArrayList<SysRole> getRoleListByCond(Map<String, Object> map);

    ArrayList<SysMenu> getMenuTree(Map<String, Object> map);

    List<SysRole> getAllRoleList();

    List<SysRole> getRoleListByPerId(Long menuId);


}
