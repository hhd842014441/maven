package com.hanhuide.security.filter;

import com.alibaba.fastjson.JSON;
import com.hanhuide.security.enums.ResultEnum;
import com.hanhuide.security.exception.CustomExpiredJwtException;
import com.hanhuide.security.handler.CustomAuthenticationDetailsSource;
import com.hanhuide.security.model.CustomAuthenticationToken;
import com.hanhuide.security.model.CustomResponseBody;
import com.hanhuide.security.service.impl.CustomUserDetailsService;
import com.hanhuide.security.utils.AccessAddressUtil;
import com.hanhuide.security.utils.DateUtil;
import com.hanhuide.security.utils.JwtTokenUtil;
import com.hanhuide.security.utils.TokenRedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截token
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.startsWith}")
    private String startsWith;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationDetailsSource authenticationDetailsSource;
    @Autowired
    private TokenRedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        log.info("token为：{}", authHeader);
        String currentIp = AccessAddressUtil.getIpAddress(request);        //获取请求的ip地址
        CustomResponseBody body = new CustomResponseBody();
        if (authHeader != null && authHeader.startsWith(startsWith)) {
            String authToken = authHeader.substring(startsWith.length());
            String ip = (String) redisUtil.getValueByToken(authToken, "ip");
            String expirationTime = (String) redisUtil.getValueByToken(authToken, "expirationTime");
            String username = (String) redisUtil.getValueByToken(authToken, "username");
            String tokenValidTime = (String) redisUtil.getValueByToken(authToken, "tokenValidTime");
//            过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）同时，已过期的token加入黑名单
            if (jwtTokenUtil.isTokenExpired(authToken)) {
                //获得redis中用户的token刷新时效
                String currentTime = DateUtil.getTime();
                log.info("是否过期{}", !DateUtil.compareDate(currentTime, tokenValidTime));
                if (!DateUtil.compareDate(currentTime, tokenValidTime)) {
                    //获取请求的ip地址
                    Map<String, Object> map = new HashMap<>();
                    map.put("ip", ip);
                    String jwtToken = jwtTokenUtil.doGenerateToken(map, username);
                    //更新redis
                    redisUtil.deleteKey(authToken);
                    redisUtil.setTokenRefresh(jwtToken, username, ip, tokenValidTime, expirationTime);
                    response.setHeader(tokenHeader, jwtToken);
                } else {
                    throw new CustomExpiredJwtException("token已过期请重新登录");
                }
            }
            if (username != null) {
//                加入对ip的验证 如果ip不正确，进入黑名单验证
                log.info("当前ip为:{}，token中id为:{}", currentIp, ip);
                if (!ip.equalsIgnoreCase(currentIp)) {//地址不正确
                    log.info("用户：{}的ip地址变动，进入黑名单校验", username);
                    //进入黑名单验证
                    if (redisUtil.isBlackList(authToken)) {
                        log.info("用户：{}的token：{}在黑名单之中，拒绝访问", username, authToken);
                        body.setStatus(ResultEnum.TOKEN_IS_BLACKLIST.getCode());
                        body.setMsg(ResultEnum.TOKEN_IS_BLACKLIST.getMessage());
                        response.getWriter().write(JSON.toJSONString(body));
                        return;
                    }
                    //黑名单没有则继续，如果黑名单存在就退出后面
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    CustomAuthenticationToken authentication =
                            new CustomAuthenticationToken(userDetails.getAuthorities(),userDetails, null,null );
                    authentication.setDetails(authenticationDetailsSource.buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
