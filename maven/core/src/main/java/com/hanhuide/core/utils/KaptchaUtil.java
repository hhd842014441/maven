package com.hanhuide.core.utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class KaptchaUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码图片
     *
     * @param request            设置session
     * @param response           转成图片
     * @param captchaProducer    生成图片方法类
     * @param validateSessionKey session名称
     * @throws Exception
     */
    public void validateCode(HttpServletRequest request, HttpServletResponse response, DefaultKaptcha captchaProducer, String validateSessionKey) throws Exception {
        /*禁止缓存*/
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        /*获取验证码*/
        String code = VerifyCodeUtils.generateVerifyCode(4);
        /*验证码已key，value的形式缓存到redis 存放时间一分钟*/
        log.info("验证码============>" + code);
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, code, 1, TimeUnit.MINUTES);
        Cookie cookie = new Cookie("captcha", uuid);
        /*key写入cookie，验证时获取*/
        response.addCookie(cookie);
        ServletOutputStream outputStream = response.getOutputStream();
        //ImageIO.write(bufferedImage,"jpg",outputStream);
        VerifyCodeUtils.outputImage(110, 40, outputStream, code);
        outputStream.flush();
        outputStream.close();
    }
}
