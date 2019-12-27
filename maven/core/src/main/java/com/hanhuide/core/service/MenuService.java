package com.hanhuide.core.service;


import com.hanhuide.core.model.AjaxResponseBody;

import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/22
 * @Description：
 */
public interface MenuService {
    AjaxResponseBody update(Map<String, Object> map);

    AjaxResponseBody add(Map<String, Object> map);

    AjaxResponseBody queryAllMenusTree(Map<String, Object> map);

    AjaxResponseBody getPerIdList(Map<String, Object> map);

    AjaxResponseBody addRP(Map<String, Object> map);

    AjaxResponseBody del(Map<String, Object> map);
}
