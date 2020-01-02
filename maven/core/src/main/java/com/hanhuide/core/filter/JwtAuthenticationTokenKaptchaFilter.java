//package com.hanhuide.core.filter;
//
//import com.hanhuide.core.service.impl.CustomUserDetailsService;
//import com.hanhuide.core.utils.CookieUtils;
//import com.hanhuide.core.utils.JwtTokenUtil;
//import com.hanhuide.toolkit.utils.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
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
//@Component
//@Slf4j
//public class JwtAuthenticationTokenKaptchaFilter extends OncePerRequestFilter {
//    private static final PathMatcher pathMatcher = new AntPathMatcher();
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private RedisUtil redisUtil;
//    @Value("${jwt.header}")
//    private String jwtHeader;
//    @Value("${jwt.startsWith}")
//    private String jwtStartsWith;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (isProtectedUrl(request)) {
//            String validateCodeText = request.getParameter("validateCodeText");
//            logger.info(validateCodeText);
//            if (kaptchaVerify(validateCodeText)) {
//                filterChain.doFilter(request, response);
//            }
//        } else {
//            String authHeader = request.getHeader(jwtHeader);
//            if (authHeader != null && authHeader.startsWith(jwtStartsWith)) {
//                final String authToken = authHeader.substring(jwtStartsWith.length());
//                String username = jwtTokenUtil.getUsernameFromToken(authToken);
//                log.info(username);
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                    if (userDetails != null) {
//                        UsernamePasswordAuthenticationToken authentication =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    }
//                }
//            }
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    private boolean kaptchaVerify(String validateCodeText) {
//        //获取当前线程绑定的request对象
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String captcha = CookieUtils.getCookieValue(request, "captcha");
//        log.info("captcha:{}", captcha);
//        String validateCode = (String) redisUtil.get(captcha);
//        validateCodeText = validateCodeText.toLowerCase();
//        log.info("验证码：" + validateCode + "用户输入：" + validateCodeText);
//        return validateCode.equals(validateCodeText);
//    }
//
//    /**
//     * 拦截请求登录页面 为post的请求
//     *
//     * @param request
//     * @return
//     */
//    private boolean isProtectedUrl(HttpServletRequest request) {
//        return "POST".equals(request.getMethod()) && pathMatcher.match("/login", request.getServletPath());
//    }
//
//    private boolean kaptcha(HttpServletRequest request) {
//        return "GET".equals(request.getMethod()) && pathMatcher.match("/kaptcha/render", request.getServletPath());
//    }
//}
