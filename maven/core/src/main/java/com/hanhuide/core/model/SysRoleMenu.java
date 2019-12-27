package com.hanhuide.core.model;

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
@TableName("sys_role_menu")
@Data
public class SysRoleMenu implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableField("ROLE_ID")
    private Long roleId;
    @TableField("MENU_ID")
    private Long menuId;


    public static final String ROLE_ID = "ROLE_ID";

    public static final String MENU_ID = "MENU_ID";

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                "}";
    }
}
