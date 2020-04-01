package com.hanhuide.security.service;

import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.model.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/22
 * @Description：
 */
public interface MenuService {
    CustomResponseBody update(Map<String, Object> map);

    CustomResponseBody add(Map<String, Object> map);

    CustomResponseBody queryAllMenusTree(Map<String, Object> map);

    CustomResponseBody getPerIdList(Map<String, Object> map);

    CustomResponseBody addRP(Map<String, Object> map);

    CustomResponseBody del(Map<String, Object> map);

    List<SysMenu> allMenu();
}
