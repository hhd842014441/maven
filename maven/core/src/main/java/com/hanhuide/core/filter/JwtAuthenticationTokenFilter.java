//package com.hanhuide.core.filter;
//
//import com.hanhuide.core.service.impl.CustomUserDetailsService;
//import com.hanhuide.core.utils.JwtTokenUtil;
//import com.hanhuide.core.utils.TokenRedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
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
//@Component
//@Slf4j
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Value("${jwt.header}")
//    private String jwtHeader;
//    @Value("${jwt.startsWith}")
//    private String jwtStartsWith;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String authHeader = request.getHeader(jwtHeader);
//        if (authHeader != null && authHeader.startsWith(jwtStartsWith)) {
//            final String authToken = authHeader.substring(jwtStartsWith.length());
//            String username = jwtTokenUtil.getUsernameFromToken(authToken);
//            log.info(username);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (userDetails != null) {
//                    UsernamePasswordAuthenticationToken authentication =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
//        chain.doFilter(request, response);
//    }
//    private boolean validateVerify(String inputVerify) {
//        //获取当前线程绑定的request对象
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 不分区大小写
//        // 这个validateCode是在servlet中存入session的名字
//        String validateCode = ((String) request.getSession().getAttribute("validateCode")).toLowerCase();
//        inputVerify = inputVerify.toLowerCase();
//
//        System.out.println("验证码：" + validateCode + "用户输入：" + inputVerify);
//        return validateCode.equals(inputVerify);
//    }
//
//    // 拦截 /login的POST请求
//    private boolean isProtectedUrl(HttpServletRequest request) {
//        return "POST".equals(request.getMethod()) && pathMatcher.match("/login", request.getServletPath());
//    }
//}
