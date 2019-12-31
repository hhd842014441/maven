//package com.hanhuide.core.handler;
//
//import com.hanhuide.core.model.CustomUserDetails;
//import com.hanhuide.core.model.SysMenu;
//import com.hanhuide.core.model.SysRole;
//import com.hanhuide.core.service.RoleService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @program: maven
// * @description:
// * @author: 韩惠德
// * @create: 2019-12-31 11:42
// * @version: 1.0
// **/
//@Component
//@Slf4j
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//    @Autowired
//    private RoleService roleService;
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
//        log.info("======================================");
//        // 获得loadUserByUsername()方法的结果
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        // 获得loadUserByUsername()中注入的角色
//        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
//        // 遍历用户所有角色
//        for (GrantedAuthority authority : authorities) {
//            String roleName = authority.getAuthority();
//            log.info("roleName{}", roleName);
//            SysRole role = roleService.selectByRoleName(roleName);
//            // 得到角色所有的权限
//            List<SysMenu> menus = role.getChildMenus();
//            // 遍历permissionList
//            for (SysMenu menu : menus) {
//                // 如果访问的Url和权限用户符合的话，返回true
//                if (targetUrl.equals(menu.getPath())) {
//                    return true;
//                }
//            }
//
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
//        return false;
//    }
//}
