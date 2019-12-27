package com.hanhuide.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hanhuide.core.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;


/**
 * @Autoor:杨文彬
 * @Date:2019/1/4
 * @Description：
 */

public interface UserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名查找用户信息
     *
     * @param username
     * @return
     */
    SysUser selectByUserName(@Param("username")String username);

    /**
     * 通过用户名称获取密码
     *
     * @param username
     * @return
     */
    String selectPasswordByUsername(String username);

    /**
     * 查看当前用户是否存在
     *
     * @param username
     * @return
     */
    Integer selectUserNameIsExist(String username);

    /**
     * 通过用户名查找用户信息
     *
     * @param username
     * @return
     */
    SysUser selectUserByUsername(String username);
}
