package com.hanhuide.core.filter;

import com.alibaba.fastjson.JSON;
import com.hanhuide.core.enums.ResultEnum;
import com.hanhuide.core.model.CustomResponseBody;
import com.hanhuide.core.service.impl.CustomUserDetailsService;
import com.hanhuide.core.utils.AccessAddressUtil;
import com.hanhuide.core.utils.DateUtil;
import com.hanhuide.core.utils.JwtTokenUtil;
import com.hanhuide.core.utils.TokenRedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenRedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        String currentIp = AccessAddressUtil.getIpAddress(request);        //获取请求的ip地址
        CustomResponseBody body = new CustomResponseBody();
        if (authHeader != null && authHeader.startsWith(startsWith)) {
            String authToken = authHeader.substring(startsWith.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(authToken);
            String ip = (String) claims.get("ip");
            /*
             * 过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）
             * 同时，已过期的token加入黑名单
             */
            if (jwtTokenUtil.isTokenExpired(authToken)) {
                //获得redis中用户的token刷新时效
                String tokenValidTime = (String) redisUtil.getTokenValidTimeByToken(authToken);
                String currentTime = DateUtil.getTime();
                if (DateUtil.compareDate(currentTime, tokenValidTime)) {
                    log.info("{}已超过有效期，不予刷新", authToken);
                    body.setStatus(ResultEnum.LOGIN_IS_OVERDUE.getCode());
                    body.setMsg(ResultEnum.LOGIN_IS_OVERDUE.getMessage());
                    response.getWriter().write(JSON.toJSONString(body));
                    return;
                } else {
                    log.info("仍在刷新时间内，则刷新token，放入请求头中");
                    ip = (String) redisUtil.getIPByToken(username);//更新ip
                    //获取请求的ip地址
                    Map<String, Object> map = new HashMap<>();
                    map.put("ip", ip);
                    String jwtToken = jwtTokenUtil.doGenerateToken(map, username);
                    //更新redis
                    redisUtil.setTokenRefresh(username, username, ip);
                    response.setHeader(tokenHeader, jwtToken);
                }
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                /*
                 * 加入对ip的验证
                 * 如果ip不正确，进入黑名单验证
                 */
                if (ip.equalsIgnoreCase(currentIp)) {//地址不正确
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
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
