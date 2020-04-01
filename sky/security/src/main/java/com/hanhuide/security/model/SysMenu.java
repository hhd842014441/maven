package com.hanhuide.security.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 韩惠德
 * @since 2019-11-29
 */
@TableName("sys_menu")
@Data
public class SysMenu implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 菜单/按钮ID
     */
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;
    /**
     * 上级菜单ID
     */
    @TableField("PARENT_ID")
    private Long parentId;
    /**
     * 菜单/按钮名称
     */
    @TableField("MENU_NAME")
    private String menuName;
    /**
     * 对应路由path
     */
    @TableField("PATH")
    private String path;
    /**
     * 对应路由组件component
     */
    @TableField("COMPONENT")
    private String component;
    /**
     * 权限标识
     */
    @TableField("PERMS")
    private String perms;
    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;
    /**
     * 类型 0菜单 1按钮
     */
    @TableField("TYPE")
    private String type;
    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Double orderNum;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    private ArrayList<SysMenu> menuChild;
}
