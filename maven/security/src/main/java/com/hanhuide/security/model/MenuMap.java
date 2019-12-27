package com.hanhuide.security.model;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lan
 * @description 权限相关
 * @date 2019/4/9 11:43
 */
@Data
public class MenuMap {

    public static HashMap<String, Collection<ConfigAttribute>> map;

    public static List<SysMenu> list;
}
