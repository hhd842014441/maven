//package com.hanhuide.core.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.PathMatcher;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
///**
// * @program: maven
// * @description:
// * @author: 韩惠德
// * @create: 2019-12-27 17:30
// * @version: 1.0
// **/
//@Slf4j
//public class kaptchaFilter extends OncePerRequestFilter {
//    private static final PathMatcher pathMatcher = new AntPathMatcher();
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (isProtectedUrl(request)) {
//            String validateCodeText = request.getParameter("validateCodeText");
//            logger.info(validateCodeText);
//            if (!kaptchaVerify(validateCodeText)) {
//                //手动设置异常
//                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new DisabledException("验证码输入错误"));
//                // 转发到错误Url
//                request.getRequestDispatcher("/login/error").forward(request, response);
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
//
//    }
//
//    private boolean kaptchaVerify(String validateCodeText) {
//        //获取当前线程绑定的request对象
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 这个validateCode是在servlet中存入session的名字code  在获取验证码的时候添加到session中
//        String validateCode = ((String) request.getSession().getAttribute("code")).toLowerCase();
//        validateCodeText = validateCodeText.toLowerCase();
//        log.info("验证码：" + validateCode + "用户输入：" + validateCodeText);
//        return validateCode.equals(validateCodeText);
//    }
//
//    /**
//     * 拦截请求登录页面 为post的请求
//     * @param request
//     * @return
//     */
//    private boolean isProtectedUrl(HttpServletRequest request) {
//        return "POST".equals(request.getMethod()) && pathMatcher.match("/login", request.getServletPath());
//    }
//}
