package com.hanhuide.security.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 韩惠德
 * @since 2019-11-29
 */
@TableName("sys_user_role")
@Data
public class SysUserRole implements Serializable{

    private static final Long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

    public static final String USER_ID = "USER_ID";

    public static final String ROLE_ID = "ROLE_ID";

    @Override
    public String toString() {
        return "SysUserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}
