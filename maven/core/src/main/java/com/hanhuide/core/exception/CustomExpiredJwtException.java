package com.hanhuide.core.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomExpiredJwtException extends AuthenticationException {
    public CustomExpiredJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomExpiredJwtException(String msg) {
        super(msg);
    }
}
