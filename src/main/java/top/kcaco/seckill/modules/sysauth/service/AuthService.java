package top.kcaco.seckill.modules.sysauth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.kcaco.seckill.common.constant.RedisConstant;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.config.properties.UserPasswordProperties;
import top.kcaco.seckill.modules.sysauth.model.form.RegisterForm;
import top.kcaco.seckill.modules.sysuser.domain.entity.SysUser;
import top.kcaco.seckill.modules.sysuser.domain.form.LoginForm;
import top.kcaco.seckill.modules.sysuser.domain.model.BaseUserInfo;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginResult;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginUser;
import top.kcaco.seckill.modules.sysuser.enums.UserStatusEnum;
import top.kcaco.seckill.modules.sysuser.service.SysUserService;
import top.kcaco.seckill.utils.AesUtil;
import top.kcaco.seckill.utils.LoginHelper;
import top.kcaco.seckill.utils.RedisUtil;
import top.kcaco.seckill.utils.ServletUtil;

import java.time.Duration;
import java.util.List;

/**
 * Description:
 *
 * @author kcaco
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final SysUserService sysUserService;

    private final UserPasswordProperties userPasswordProperties;

    /**
     * 登录
     *
     * @param loginForm 请求体
     * @return 登录结果
     */
    public LoginResult login(LoginForm loginForm) {
        // 检查登录
        LoginUser loginUser = checkLogin(loginForm, ServletUtil.getRequest());

        // 登录
        LoginHelper.login(loginUser);

        // 记录登录信息
        recordLoginInfo(loginUser.getId());

        // 返回信息
        LoginResult loginResult = new LoginResult();
        loginResult.setAccessToken(StpUtil.getTokenValue());
        return loginResult;
    }

    /**
     * 检查登录信息
     *
     * @param loginReq 请求参数
     * @param request  请求
     * @return 用户信息
     */
    private LoginUser checkLogin(LoginForm loginReq, HttpServletRequest request) {
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();

        // 检查验证码
        // String verifyCode = RedisUtils.getCacheObject(RedisConstant.VERIFICATION_CODE + loginReq.getTimestamp());
        // if (StrUtil.isBlank(verifyCode)) {
        //     throw new RuntimeException("验证码已失效");
        // }
        // if (!StrUtil.equalsIgnoreCase(verifyCode, loginReq.getVerifyCode())) {
        //     throw new RuntimeException("验证码错误");
        // }
        // RedisUtils.deleteObject(RedisConstant.VERIFICATION_CODE + loginReq.getTimestamp());

        // 检查用户
        SysUser dbSysUser = sysUserService.selectByUsername(username);
        if (ObjectUtil.isNull(dbSysUser)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 对比密码
        checkPassword(username, password, dbSysUser.getPassword());

        // 检查状态
        if (StrUtil.equals(dbSysUser.getStatus(), UserStatusEnum.DISABLED.getCode())) {
            throw new RuntimeException("该账号已被禁用，请联系系统管理员!");
        }

        return new LoginUser(new BaseUserInfo().convertBySysUser(dbSysUser), request);
    }

    /**
     * 检查密码
     *
     * @param username      用户名
     * @param inputPassword 用户输入的密码
     * @param dbPassword    数据库密码
     */
    public void checkPassword(String username, String inputPassword, String dbPassword) {
        String errorKey = RedisConstant.PWD_ERR_CNT_KEY + username;
        Integer maxRetryCount = userPasswordProperties.getMaxRetryCount();
        Integer lockTime = userPasswordProperties.getLockTime();

        // 获取用户登录错误次数(可自定义限制策略 例如: key + username + ip)
        Integer errorNumber = RedisUtil.getCacheObject(errorKey);
        // 锁定时间内登录 则踢出
        if (ObjectUtil.isNotNull(errorNumber) && errorNumber.equals(maxRetryCount)) {
            throw new RuntimeException("输入密码错误次数已达到上限，请" + lockTime + "分钟后再试~");
        }

        String decryptPassword = AesUtil.decryptCbc(inputPassword);
        if (!BCrypt.checkpw(decryptPassword, dbPassword)) {
            // 是否第一次
            errorNumber = ObjectUtil.isNull(errorNumber) ? 1 : errorNumber + 1;
            // 达到规定错误次数 则锁定登录
            if (errorNumber.equals(maxRetryCount)) {
                RedisUtil.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                throw new RuntimeException("输入密码错误次数已达到上限，请" + lockTime + "分钟后再试~");
            } else {
                // 未达到规定错误次数 则递增
                RedisUtil.setCacheObject(errorKey, errorNumber);
                throw new RuntimeException("用户名或密码错误，错误次数：【" + errorNumber + "次】，最大次数【" + maxRetryCount + "次】");
            }
        }
        // 登录成功 清空错误次数
        RedisUtil.deleteObject(errorKey);
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户id
     */
    public void recordLoginInfo(String userId) {
        SysUser updateSysUser = SysUser.builder()
                .id(userId)
                .recentLogin(DateUtil.date())
                .build();
        sysUserService.updateById(updateSysUser);
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return 角色
     */
    public List<String> getUserRoleList(String userId) {
        SysUser sysUser = sysUserService.getById(userId);
        return ListUtil.toList(sysUser.getRole());
    }

    /**
     * 退出登录
     */
    public Result logout() {
        String userId = LoginHelper.getUserId();
        StpUtil.logout(userId);
        return Result.success();
    }

    /**
     * 用户注册
     *
     * @param registerForm 用户信息
     * @return
     */
    public Result register(RegisterForm registerForm) {
        // 检查用户信息
        checkRegister(registerForm);

        // 新增用户
        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerForm.getUsername());
        sysUser.setSalt(BCrypt.gensalt());

//        String decryptPassword = AesUtil.decryptCbc(registerDto.getPassword());
        String decryptPassword = registerForm.getPassword();
        sysUser.setPassword(BCrypt.hashpw(decryptPassword, sysUser.getSalt()));
        sysUser.setEmail(registerForm.getEmail());
        sysUser.setCreateBy("");
        sysUserService.save(sysUser);

        return Result.success();
    }

    /**
     * 检查用户注册信息
     *
     * @param registerForm 用户注册信息
     */
    public void checkRegister(RegisterForm registerForm) {
        // 检查数据
        if (StrUtil.isBlank(registerForm.getUsername())) {
            throw new RuntimeException("用户名不能为空!");
        }
        if (StrUtil.isBlank(registerForm.getEmail())) {
            throw new RuntimeException("邮箱不能为空!");
        }
        if (StrUtil.isBlank(registerForm.getPassword())) {
            throw new RuntimeException("密码不能为空!");
        }
        if (StrUtil.isBlank(registerForm.getCode())) {
            throw new RuntimeException("验证码不能为空!");
        }

        // 获取验证码
        String code = RedisUtil.getCacheObject(RedisConstant.VERIFICATION_CODE + registerForm.getEmail());
        if (StrUtil.isBlank(code)) {
            throw new RuntimeException("验证码已过期，请重新申请!");
        }

        // 检查验证码
        if (!StrUtil.equals(registerForm.getCode(), code)) {
            throw new RuntimeException("验证码错误！");
        }
        RedisUtil.deleteObject(RedisConstant.VERIFICATION_CODE + registerForm.getEmail());

        // 查询是否有相同用户名的用户
        SysUser adminByUsername = sysUserService.getUserByUsername(registerForm.getUsername());
        if (ObjectUtil.isNotNull(adminByUsername)) {
            throw new RuntimeException("用户名重复");
        }
        // 查询是否有相同邮箱的用户
        SysUser emailUser = sysUserService.getUserByEmail(registerForm.getEmail());
        if (ObjectUtil.isNotNull(emailUser)) {
            throw new RuntimeException("邮箱重复");
        }
    }

    /**
     * 获取当前用户信息
     */
    public BaseUserInfo getCurrentUserInfo() {
        return sysUserService.getUserInfo(StpUtil.getLoginIdAsString());
    }
}
