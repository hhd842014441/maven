package com.hanhuide.security.controller;

import com.hanhuide.security.model.AjaxResponseBody;
import com.hanhuide.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Autoor:杨文彬
 * @Date:2019/1/21
 * @Description：
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", allowCredentials= "true")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 更新角色信息
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/roles")
    public AjaxResponseBody update(@RequestBody Map<String,Object> map){
        return roleService.updateById(map);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/roles/{id}")
    public AjaxResponseBody del(@PathVariable Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return roleService.delRoleById(map);
    }

    /**
     * 添加角色
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/roles")
    public AjaxResponseBody add(@RequestBody Map<String,Object> map){
        return roleService.addRoleById(map);
    }

    /**
     * 获取角色列表
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/getRoleList",method = RequestMethod.POST)
    public AjaxResponseBody getRoleList(@RequestBody Map<String,Object> map){
        return roleService.getRoleListByCond(map);
    }

    /**
     * 获取全部角色列表
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/getAllRoleList",method = RequestMethod.POST)
    public AjaxResponseBody getAllRoleList(@RequestBody(required = false) Map<String, Object> map){
        return roleService.getAllRoleList(map);
    }

    /**
     * 根据权限id查询角色列表
     * @param map
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/getRoleListByPerId")
    public AjaxResponseBody getRoleListByPerId(@RequestBody(required = false) Map<String, Object> map){
        return roleService.getRoleListByPerId(map);
    }
}
