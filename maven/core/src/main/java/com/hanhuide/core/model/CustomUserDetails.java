package com.hanhuide.core.model;

import com.hanhuide.core.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * CustomerUserDetails
 *
 * @author zgd
 * @date 2019/7/17 15:29
 */
public class CustomUserDetails extends SysUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(SysUser user){
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setStatus(user.getStatus());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 添加用户拥有的权限和角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

