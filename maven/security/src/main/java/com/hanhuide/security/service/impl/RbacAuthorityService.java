package com.hanhuide.security.service.impl;

import com.hanhuide.security.mapper.UserMapper;
import com.hanhuide.security.model.SelfUserDetails;
import com.hanhuide.security.service.RoleService;
import com.hanhuide.security.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: maven
 * @description: 访问权限控制
 * @author: 韩惠德
 * @create: 2019-12-04 13:48
 * @version: 1.0
 **/
@Component("rbacauthorityservice")
public class RbacAuthorityService {
    @Autowired
    private RoleService roleService;
    /**
     * uri匹配工具
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof SelfUserDetails) {
            String username = ((SelfUserDetails) userInfo).getUsername();
            //admin永远放回true
            if (StringUtils.equals("admin", username)) {
                hasPermission = true;
            } else {
                //读取用户所拥有权限所有的URL 在这里全部返回true
                Set<String> urls = urls();
                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
            return hasPermission;
        } else {
            return hasPermission;
        }
    }

    private Set<String> urls() {
        return null;
    }
}
