package top.kcaco.seckill.utils;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.kcaco.seckill.common.enums.RoleEnum;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginUser;

import java.util.List;

/**
 * 登录鉴权助手
 *
 * @author kcaco
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER = "loginUser";
    public static final String USER_NAME = "username";
    public static final String USER_SESSION_KEY = "sessionKey";

    /**
     * 登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void login(LoginUser loginUser) {
        StpUtil.login(loginUser.getId(),
                SaLoginConfig
                        .setExtra(USER_NAME, loginUser.getUsername()));
        StpUtil.getSession().set(LOGIN_USER, loginUser);
    }

    /**
     * 微信登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void wechatLogin(LoginUser loginUser, String sessionKey) {
        StpUtil.login(loginUser.getId(),
                SaLoginConfig
                        .setExtra(USER_NAME, loginUser.getUsername())
                        .setExtra(USER_SESSION_KEY, sessionKey));
        StpUtil.getSession().set(LOGIN_USER, loginUser);
    }

    /**
     * 获取用户
     */
    public static LoginUser getLoginUser() {
        SaSession session = StpUtil.getSession();
        return (LoginUser) session.get(LOGIN_USER);
    }

    /**
     * 设置用户信息缓存
     */
    public static void setLoginUser(LoginUser loginUser) {
        StpUtil.getSession().set(LOGIN_USER, loginUser);
    }

    /**
     * 删除用户信息缓存
     */
    public static void delLoginUser(String loginId) {
        SaSession saSession = StpUtil.getSessionByLoginId(loginId);
        if (ObjectUtil.isNull(saSession)) {
            return;
        }
        saSession.delete(LOGIN_USER);
    }

    /**
     * 获取用户id
     */
    public static String getUserId() {
        return StpUtil.getLoginIdAsString();
    }

    /**
     * 获取用户角色
     */
    public static String getUserRole() {
        return getLoginUser().getRole();
    }

    /**
     * 获取用户角色
     */
    public static String getUserRole(String userId) {
        List<String> roles = StpUtil.getRoleList(userId);
        return roles.get(0);
    }

    /**
     * 是否为管理员
     *
     * @param role 用户角色
     * @return 结果
     */
    public static boolean isAdmin(String role) {
        if (StrUtil.isBlank(role)) {
            return false;
        }

        return StrUtil.equals(role, RoleEnum.ADMIN.getCode());
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return (String) StpUtil.getExtra(USER_NAME);
    }

    /**
     * 是否为管理员
     */
    public static boolean isAdmin() {
        return isAdmin(getLoginUser().getRole());
    }

}
