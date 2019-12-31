package com.hanhuide.core.service.impl;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.hanhuide.core.model.CustomUserDetails;
import com.hanhuide.core.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: maven
 * @description:用户认证、权限
 * @author: 韩惠德
 * @create: 2019-12-26 11:59
 * @version: 1.0
 **/
@Service("userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info(">>>>>>>>> username:{}", userName);
        SysUser user = userService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        if (CollectionUtils.isNotEmpty(user.getChildRole())) {
            user.getChildRole().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
        }
        userDetails.setAuthorities(authorities);
        log.info("authorities:{}", authorities);
        //返回的是我们自己定义的UserDetail
        return userDetails;//密码必须加密
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }
}
