package top.kcaco.seckill.modules.sysuser.domain.model;


import lombok.Data;
import top.kcaco.seckill.common.enums.GenderEnum;
import top.kcaco.seckill.modules.sysuser.domain.entity.SysUser;

import java.io.Serializable;

/**
 * 基础用户信息
 *
 * @author kcaco
 */
@Data
public class BaseUserInfo implements Serializable {

    /**
     * 用户id
     */
    private String id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     * {@link GenderEnum}
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人介绍
     */
    private String info;

    /**
     * 状态
     */
    private String status;

    /**
     * 角色
     */
    private String role;

    public BaseUserInfo convertBySysUser(SysUser sysUser) {
        BaseUserInfo baseUserInfo = new BaseUserInfo();
        baseUserInfo.setId(sysUser.getId());
        baseUserInfo.setAvatar(sysUser.getAvatar());
        baseUserInfo.setUsername(sysUser.getUsername());
        baseUserInfo.setNickname(sysUser.getNickname());
        baseUserInfo.setGender(sysUser.getGender());
        baseUserInfo.setEmail(sysUser.getEmail());
        baseUserInfo.setInfo(sysUser.getInfo());
        baseUserInfo.setStatus(sysUser.getStatus());
        baseUserInfo.setRole(sysUser.getRole());
        return baseUserInfo;
    }
}
