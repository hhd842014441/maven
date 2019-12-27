package com.hanhuide.core.service;


import com.hanhuide.core.model.AjaxResponseBody;

import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/7
 * @Description：
 */
public interface RoleService {

    AjaxResponseBody getRoleListByCond(Map<String, Object> map);

    AjaxResponseBody getAllRoleList(Map<String, Object> map);

    AjaxResponseBody getRoleListByPerId(Map<String, Object> map);

    AjaxResponseBody selectByUserName(Map<String, Object> map);

    AjaxResponseBody addRoleById(Map<String, Object> map);

    AjaxResponseBody delRoleById(Map<String, Object> map);

    AjaxResponseBody updateById(Map<String, Object> map);
}
