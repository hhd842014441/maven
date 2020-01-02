package com.hanhuide.core.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hanhuide.core.utils.CookieUtils;
import com.hanhuide.core.utils.VerifyCodeUtils;
import com.hanhuide.toolkit.utils.RedisUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

//
//    @RequestMapping("/render")
//    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //生产验证码字符串并保存到session中
//            String createText = defaultKaptcha.createText();
//            log.info(createText);
//            String uuid = UUID.randomUUID().toString();
//            CookieUtils.setCookie(httpServletRequest,httpServletResponse,"captcha",createText);
//            httpServletRequest.getSession().setAttribute("code", createText);
//            redisUtil.set(uuid, createText, 60);
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }

    //验证码验证
    @RequestMapping("/checkCode")
    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("code");
        log.info("Session  vrifyCode ---->" + captchaId + "---- form code --->" + parameter);
        if (!captchaId.equals(parameter)) {
            log.info("错误的验证码");
            return false;
        } else {
            return true;
        }
    }
}
