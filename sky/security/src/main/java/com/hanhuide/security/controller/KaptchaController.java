package com.hanhuide.security.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hanhuide.security.utils.CookieUtils;
import com.hanhuide.security.utils.VerifyCodeUtils;
import com.hanhuide.toolkit.utils.RedisUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Api(tags = "验证码")
@Slf4j
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    private VerifyCodeUtils verifyCodeUtils;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/render")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        /*禁止缓存*/
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        /*获取验证码*/
        String createText = defaultKaptcha.createText();
        /*验证码已key，value的形式缓存到redis 存放时间一分钟*/
        String uuid = UUID.randomUUID().toString();
        redisUtil.set(uuid, createText, 60);
        CookieUtils.setCookie(httpServletRequest,response,"captcha",uuid);
        /*key写入cookie，验证时获取*/
        ServletOutputStream outputStream = response.getOutputStream();
        //ImageIO.write(bufferedImage,"jpg",outputStream);
        verifyCodeUtils.outputImage(110, 40, outputStream, createText);
        outputStream.flush();
        outputStream.close();
    }
}
