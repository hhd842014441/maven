package com.hanhuide.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-31 15:45
 * @version: 1.0
 **/
@Slf4j
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("principal:{} collection:{}", authentication.getPrincipal().toString(), collection);
        for (ConfigAttribute configAttribute : collection) {
            // 当前请求需要的权限
            String needRole = configAttribute.getAttribute();
            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            log.info("authorities: {}", authorities);
            for (GrantedAuthority grantedAuthority : authorities) {
                // 包含其中一个角色即可访问
                if (grantedAuthority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("SimpleGrantedAuthority!!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
