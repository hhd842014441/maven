package com.hanhuide.security.utils;

import com.hanhuide.security.exception.CustomExpiredJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;

    private Clock clock = DefaultClock.INSTANCE;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public static void main(String[] args) {
        String tokemn = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiaXAiOiIwOjA6MDowOjA6MDowOjEiLCJleHAiOjE1Nzc5NTg4NDYsImlhdCI6MTU3Nzk1ODU0Nn0.78aGErlWt6mmkJGtHHyXiePSJa5kDtTS6OtGs0npB6-kA0u1myB6ptRM8qmghFW_d5KCYXAzmzv9zbotFaJZ0g";
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    public Boolean isTokenExpired(String token) {
        boolean flag = true;
        try {
            final Date expiration = getExpirationDateFromToken(token);
            flag = expiration.before(clock.now());
        } catch (Exception e) {
            log.error("(signing key cannot be null or empty) or token 过期");
        }
        return flag;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
}
