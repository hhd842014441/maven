package com.hanhuide.core.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 韩惠德
 * @since 2019-11-29
 */
@TableName("sys_role")
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;
    /**
     * 角色描述
     */
    @TableField("REMARK")
    private String remark;
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


    private List<SysMenu> childMenus;
}
