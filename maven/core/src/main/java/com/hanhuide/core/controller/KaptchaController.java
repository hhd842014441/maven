package com.hanhuide.core.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Api(tags = "验证码")
@Slf4j
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/render")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            log.info(createText);
            httpServletRequest.getSession().setAttribute("code", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    //验证码验证
    @RequestMapping("/checkCode")
    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("code");
        log.info("Session  vrifyCode ---->"+captchaId+"---- form code --->"+parameter);
        if (!captchaId.equals(parameter)) {
            log.info("错误的验证码");
            return false;
        } else {
            return true;
        }
    }
}
