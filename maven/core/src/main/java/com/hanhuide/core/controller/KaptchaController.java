package com.hanhuide.core.controller;

import com.baomidou.kaptcha.Kaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "验证码")
@Slf4j
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    @ApiOperation(value = "获取图形验证码", notes = "图形验证")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }

    @PostMapping("/validTime")
    public void validCustomTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }

}
