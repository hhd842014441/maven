//package com.hanhuide.security.filter;
//
//import com.hanhuide.security.handler.*;
//import com.hanhuide.security.model.CustomAuthDetails;
//import com.hanhuide.security.model.CustomAuthenticationToken;
//import com.hanhuide.security.utils.JwtTokenUtil;
//import com.hanhuide.security.utils.TokenRedisUtil;
//import com.nimbusds.oauth2.sdk.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @ProjectName: sky
// * @Package: com.hanhuide.security.filter
// * @ClassName: CustormAuthenticationFilter
// * @Author: 韩惠德
// * @Description:
// * @Date: 2020/3/22 23:13
// * @Version: 1.0
// */
//@Slf4j
//@Component
//public class CustormAuthenticationFilter extends OncePerRequestFilter {
//    @Value("${jwt.header}")
//    private String tokenHeader;
//    @Value("${jwt.startsWith}")
//    private String startsWith;
//    private List<RequestMatcher> permissiveRequestMatchers;
//    @Autowired
//    private TokenRedisUtil tokenRedisUtil;
//    @Autowired
//    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）
//    @Autowired
//    private CustomAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）
//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;
//    @Autowired
//    private CustomAuthenticationDetailsSource authenticationDetailsSource;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Authentication authResult = null;
//        AuthenticationException failed = null;
//        try {
//            String authHeader = request.getHeader(tokenHeader);
//            log.info(authHeader);
//            if (StringUtils.isNotBlank(authHeader)) {
//                String token = authHeader.substring(startsWith.length());
//                CustomAuthenticationToken authToken = new CustomAuthenticationToken(token);
//                authResult = customAuthenticationProvider.authenticate(authToken);
//            } else {
//                CustomAuthenticationToken authentication = new CustomAuthenticationToken();
//                authentication.setDetails(authenticationDetailsSource.buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (InternalAuthenticationServiceException e) {
//            log.error("An internal error occurred while trying to authenticate the user.", failed);
//            failed = e;
//        } catch (AuthenticationException e) {
//            failed = e;
//        }
//        if (authResult != null) {
//            successfulAuthentication(request, response, filterChain, authResult);
//        } else if (!permissiveRequest(request)) {
//            unsuccessfulAuthentication(request, response, failed);
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        SecurityContextHolder.clearContext();
//        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
//    }
//
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
//    }
//
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        RequestHeaderRequestMatcher requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
//        return requiresAuthenticationRequestMatcher.matches(request);
//    }
//
//    protected boolean permissiveRequest(HttpServletRequest request) {
//        if (permissiveRequestMatchers == null)
//            return false;
//        for (RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
//            if (permissiveMatcher.matches(request))
//                return true;
//        }
//        return false;
//    }
//
//}
