package top.kcaco.seckill.modules.sysuser.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import top.kcaco.seckill.common.base.BaseEntity;
import top.kcaco.seckill.common.enums.RoleEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户(SysUser)表实体类
 *
 * @author kcaco
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_user")
public class SysUser extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户密码加密盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 角色
     * {@link RoleEnum}
     */
    @TableField(value = "role")
    private String role;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 个人说明
     */
    @TableField(value = "info")
    private String info;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 最近一次登录时间
     */
    @TableField(value = "recent_login")
    private Date recentLogin;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;
}

