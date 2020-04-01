package com.hanhuide.security.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.security.model.SysMenu;
import com.hanhuide.security.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/7
 * @Description：
 */
public interface RoleMapper extends BaseMapper<SysRole> {

    Integer add(SysRole sysRole);

    Integer del(Long roleId);

    Integer update(SysRole sysRole);

    Set<Object> selectByUserName(String username);

    SysRole selectByRoleName(@Param("roleName") String roleName);

    ArrayList<SysRole> getRoleListByCond(Map<String, Object> map);

    ArrayList<SysMenu> getMenuTree(Map<String, Object> map);

//    @Select("select sr.ROLE_ID, sr.ROLE_NAME,sm.PATH,sm.MENU_NAME\n" +
//            "        from  sys_role sr\n" +
//            "        LEFT JOIN sys_role_menu srm on srm.ROLE_ID = sr.ROLE_ID\n" +
//            "        LEFT JOIN sys_menu sm on sm.MENU_ID = srm.MENU_ID")
    List<SysRole> getAllRoleList();

    List<SysRole> getRoleListByPerId(Long menuId);


}
