package com.hanhuide.security.model;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @ProjectName: sky
 * @Package: com.hanhuide.security.model
 * @ClassName: CustomAuthenticationToken
 * @Author: 韩惠德
 * @Description:
 * @Date: 2020/3/22 22:59
 * @Version: 1.0
 */
@Data
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 3981518947978158945L;

    private Object principal;
    private Object credentials;
    private String token;

    public CustomAuthenticationToken(String token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public CustomAuthenticationToken() {
        super(Collections.emptyList());
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials, String token) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.token = token;
    }
}
