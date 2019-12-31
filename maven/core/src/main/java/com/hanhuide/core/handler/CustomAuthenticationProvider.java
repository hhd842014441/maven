package com.hanhuide.core.handler;

import com.hanhuide.core.model.CustomAuthDetails;
import com.hanhuide.core.service.impl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-30 14:44
 * @version: 1.0
 **/
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthDetails details = (CustomAuthDetails) authentication.getDetails();
        String validateCodeText = details.getVerifyCode();
        if (StringUtils.isEmptyOrWhitespace(validateCodeText)) {
            throw new DisabledException("请输入验证码");
        }
        if (!kaptchaVerify(validateCodeText)) {
            throw new DisabledException("验证码输入错误");
        }
        // userDetails为数据库中查询到的用户信息
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(details.getUsername());
        // 如果是自定义AuthenticationProvider，需要手动密码校验
        log.info("userDetails{}", userDetails);
        log.info(details.getPassword());
        log.info(new BCryptPasswordEncoder().encode(details.getPassword()));

        if (!new BCryptPasswordEncoder().matches(details.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }

        return new UsernamePasswordAuthenticationToken(details.getUsername(), new BCryptPasswordEncoder().encode(details.getPassword()), userDetails.getAuthorities());
    }

    private boolean kaptchaVerify(String validateCodeText) {
        //获取当前线程绑定的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 这个validateCode是在servlet中存入session的名字
        String validateCode = null;
        try {
            //第一次登录 会先去获取session但是，如有设置了自动登录会有，否则出现异常
            validateCode = ((String) request.getSession().getAttribute("code")).toLowerCase();
            validateCodeText = validateCodeText.toLowerCase();
            log.info("验证码：" + validateCode + "用户输入：" + validateCodeText);
            return validateCode.equals(validateCodeText);
        } catch (Exception e) {
            log.error("当前数据没有保存session");
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 这里不要忘记，和UsernamePasswordAuthenticationToken比较
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
