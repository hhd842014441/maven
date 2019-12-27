package com.hanhuide.core.controller;

import com.hanhuide.core.mapper.CeshiMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hanhuide.core.model.SysUser;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: maven
 * @description:
 * @author: 韩惠德
 * @create: 2019-12-24 16:41
 * @version: 1.0
 **/
@RestController
@Slf4j
public class Contrller11 {
    @Resource
    private CeshiMapper ceshiMapper;

    @ApiOperation(value = "测试数据源", notes = "测试数据源")
    @GetMapping("ceshi")
    public List<SysUser> ceshi() {
        return ceshiMapper.findAll();
    }

    @ApiOperation(value = "测试数据源2", notes = "测试数据源2")
    @GetMapping("ceshi2")
    public List<SysUser> ceshi2() {
        return ceshiMapper.findAll2();
    }


}