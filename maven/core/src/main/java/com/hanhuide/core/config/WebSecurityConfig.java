package com.hanhuide.core.config;

//import com.hanhuide.core.filter.JwtAuthenticationTokenFilter;

import com.hanhuide.core.filter.JwtAuthenticationTokenFilter;
import com.hanhuide.core.handler.CustomFilterInvocationSecurityMetadataSource;
import com.hanhuide.core.handler.*;
import com.hanhuide.core.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-26 11:56
 * @version: 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationDetailsSource authenticationDetailsSource;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    private CustomAccessDecisionManager decisionManager;
    @Autowired
    private JwtAuthenticationTokenFilter filter;

    /**
     * 自定义的登录认证取代原有的security认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(securityMetadataSource); //动态获取url权限配置
                o.setAccessDecisionManager(decisionManager); //权限判断
                return o;
            }
        });
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll().authenticationDetailsSource(authenticationDetailsSource).successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).permitAll();
        http.logout().logoutSuccessHandler(logoutSuccessHandler);
        http.rememberMe().rememberMeParameter("remember-me").userDetailsService(userDetailsService).tokenValiditySeconds(10000);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        //使用jwt的Authentication,来解析过来的请求是否有token
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        //配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.sessionManagement().invalidSessionUrl("/invalid").maximumSessions(1).maxSessionsPreventsLogin(false).expiredSessionStrategy(new CustomExpiredSessionStrategy());
        // 当达到最大值时，旧用户被踢出后的操作;
        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**", "/images/**", "/vendor/**")
                .antMatchers("/kaptcha/render","/favicon.ico");
    }
}
