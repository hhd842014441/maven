package com.hanhuide.security.filter;//package com.hanhuide.core.filter;
//
//import com.hanhuide.core.handler.CustomAccessDecisionManager;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
//import org.springframework.security.access.intercept.InterceptorStatusToken;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * 自定义的权限验证过滤器
// */
//@Component
//@Data
//public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
//
//    /**
//     * 注入自定义的资源（url）权限（角色）获取类
//     */
//    @Autowired
//    private CustomFilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    /**
//     * 注入自定义的权限验证管理器
//     */
//    @Autowired
//    public void setAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
//        super.setAccessDecisionManager(customAccessDecisionManager);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        FilterInvocation fi = new FilterInvocation(request, response, chain);
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//        try {
//            /**
//             * 执行下一个拦截器
//             */
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//        } finally {
//            super.afterInvocation(token, null);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//}