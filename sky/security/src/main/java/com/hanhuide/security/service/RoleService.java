package com.hanhuide.security.service;


import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/7
 * @Description：
 */
public interface RoleService {

    CustomResponseBody getRoleListByCond(Map<String, Object> map);

    CustomResponseBody getAllRoleList(Map<String, Object> map);

    CustomResponseBody getRoleListByPerId(Map<String, Object> map);

    CustomResponseBody selectByUserName(Map<String, Object> map);

    SysRole selectByRoleName(String roleName);


    List<SysRole> selectBysRole();

    CustomResponseBody addRoleById(Map<String, Object> map);

    CustomResponseBody delRoleById(Map<String, Object> map);

    CustomResponseBody updateById(Map<String, Object> map);
}
