package com.hanhuide.core.handler;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-27 13:29
 * @version: 1.0
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = KaptchaException.class)
    public String kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return "验证码不正确";
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return "验证码未找到";
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return "验证码过期";
        } else {
            return "验证码渲染失败";
        }

    }

}
