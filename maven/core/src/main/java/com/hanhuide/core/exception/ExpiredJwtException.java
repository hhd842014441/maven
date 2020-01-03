package com.hanhuide.core.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredJwtException extends AuthenticationException {
    public ExpiredJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public ExpiredJwtException(String msg) {
        super(msg);
    }
}
