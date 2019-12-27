package com.hanhuide.security.model;

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
@TableName("sys_user")
@Data
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;
    /**
     * 部门ID
     */
    @TableField("DEPT_ID")
    private Long deptId;
    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;
    /**
     * 联系电话
     */
    @TableField("MOBILE")
    private String mobile;
    /**
     * 状态 0锁定 1有效
     */
    @TableField("STATUS")
    private String status;
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
    /**
     * 最近访问时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
    /**
     * 性别 0男 1女 2保密
     */
    @TableField("SSEX")
    private String ssex;
    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 用户头像
     */
    @TableField("AVATAR")
    private String avatar;

    private List<SysRole> childRole;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static final String USER_ID = "USER_ID";

    public static final String USERNAME = "USERNAME";

    public static final String PASSWORD = "PASSWORD";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String EMAIL = "EMAIL";

    public static final String MOBILE = "MOBILE";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String LAST_LOGIN_TIME = "LAST_LOGIN_TIME";

    public static final String SSEX = "SSEX";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String AVATAR = "AVATAR";


    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", deptId=" + deptId +
                ", email=" + email +
                ", mobile=" + mobile +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", lastLoginTime=" + lastLoginTime +
                ", ssex=" + ssex +
                ", description=" + description +
                ", avatar=" + avatar +
                "}";
    }
}
