package com.hanhuide.core.handler;

import com.hanhuide.core.mapper.RoleMapper;
import com.hanhuide.core.model.SysMenu;
import com.hanhuide.core.model.SysRole;
import com.hanhuide.core.service.MenuService;
import com.hanhuide.core.service.RoleService;
import com.hanhuide.core.service.impl.RoleServiceImpl;
import com.hanhuide.core.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: maven
 * @description: 配置认证数据源
 * @author: 韩惠德
 * @create: 2019-12-31 15:05
 * @version: 1.0
 **/
@Component
@Slf4j
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private RoleServiceImpl roleService;

    @Autowired
    private UserServiceImpl userService;

    public Map<RequestMatcher, Collection<ConfigAttribute>> init() {
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        List<SysRole> list = roleService.selectBysRole();
        for (SysRole sysRole : list) {
            List<SysMenu> sysMenus = sysRole.getChildMenus();
            ArrayList<ConfigAttribute> configs = new ArrayList<>();
            configs.add(new SecurityConfig(sysRole.getRoleName()));
            for (SysMenu sysMenu : sysMenus) {
                AntPathRequestMatcher matcher = null;
                try {
                    matcher = new AntPathRequestMatcher(sysMenu.getPath());
                    configs.addAll(map.get(matcher));
                } catch (Exception e) {
                }
                if (matcher == null) continue;
                map.put(matcher, configs);
            }
        }
        return map;
    }

    /**
     * 在初始化的权限数据中找到对应当前url的权限数据
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        log.info("请求的url:{}", fi.getRequestUrl());
        HttpServletRequest request = fi.getRequest();
        //遍历我们初始化的权限数据，找到对应的url对应的权限
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : init().entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return SecurityConfig.createList("ROLE_ANONYMOUS");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
