package top.kcaco.seckill.modules.sysuser.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.kcaco.seckill.modules.sysuser.domain.entity.SysUser;
import top.kcaco.seckill.modules.sysuser.domain.model.BaseUserInfo;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginUser;
import top.kcaco.seckill.modules.sysuser.domain.request.UpdatePasswordReq;
import top.kcaco.seckill.modules.sysuser.domain.request.UpdateProfileInfoReq;
import top.kcaco.seckill.modules.sysuser.mapper.SysUserMapper;
import top.kcaco.seckill.utils.AesUtil;
import top.kcaco.seckill.utils.LoginHelper;
import top.kcaco.seckill.utils.ServletUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户(SysUser)表服务接口
 *
 * @author kcaco
 */
@Service
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    /**
     * 获取当前用户信息
     *
     * @return 登录用户信息
     */
    public LoginUser loginUserInfo() {
        // 从数据库中查询
        String userId = LoginHelper.getUserId();
        SysUser sysUser = baseMapper.selectById(userId);
        BaseUserInfo baseUserInfo = new BaseUserInfo().convertBySysUser(sysUser);
        LoginUser loginUser = new LoginUser(baseUserInfo, ServletUtil.getRequest());
        // 设置缓存
        LoginHelper.setLoginUser(loginUser);
        return loginUser;
    }

    /**
     * 更新个人信息
     *
     * @param updateProfileInfoReq 请求体
     */
    public LoginUser updateProfileInfo(HttpServletRequest request, UpdateProfileInfoReq updateProfileInfoReq) {
        if (StrUtil.isBlank(updateProfileInfoReq.getUsername())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StrUtil.isBlank(updateProfileInfoReq.getEmail())) {
            throw new RuntimeException("邮箱不能为空");
        }

        String currentUserId = StpUtil.getLoginIdAsString();

        // 查询是否有相同用户名的用户
        SysUser dbByUsername = getUserByUsername(updateProfileInfoReq.getUsername());
        if (ObjectUtil.isNotNull(dbByUsername) && !StrUtil.equals(dbByUsername.getId(), currentUserId)) {
            throw new RuntimeException("用户名已存在!");
        }

        // 更新用户
        SysUser updateUser = new SysUser();
        updateUser.setId(currentUserId);
        updateUser.setUsername(updateProfileInfoReq.getUsername());
        updateUser.setEmail(updateProfileInfoReq.getEmail());
        updateUser.setInfo(updateProfileInfoReq.getInfo());
        baseMapper.updateById(updateUser);

        // 返回用户信息
        SysUser sysUser = baseMapper.selectById(currentUserId);
        return new LoginUser(new BaseUserInfo().convertBySysUser(sysUser), request);
    }

    /**
     * 更新密码
     *
     * @param updatePasswordReq 请求体
     */
    public void updatePassword(UpdatePasswordReq updatePasswordReq) {
        if (StrUtil.isBlank(updatePasswordReq.getOldPassword())) {
            throw new RuntimeException("旧密码不能为空");
        }
        if (StrUtil.isBlank(updatePasswordReq.getNewPassword())) {
            throw new RuntimeException("新密码不能为空");
        }

        String currentUserId = StpUtil.getLoginIdAsString();

        // 检查密码
        SysUser dbSysUser = baseMapper.selectById(currentUserId);
        if (ObjectUtil.isNull(dbSysUser)) {
            throw new RuntimeException("不存在对应用户");
        }
        String decryptOldPassword = AesUtil.decryptCbc(updatePasswordReq.getOldPassword());
        if (!StrUtil.equals(dbSysUser.getPassword(), BCrypt.hashpw(decryptOldPassword, dbSysUser.getSalt()))) {
            throw new RuntimeException("密码错误");
        }

        // 更新密码
        SysUser updateUser = new SysUser();
        updateUser.setId(currentUserId);
        updateUser.setSalt(BCrypt.gensalt());
        String decryptNewPassword = AesUtil.decryptCbc(updatePasswordReq.getNewPassword());
        updateUser.setPassword(BCrypt.hashpw(decryptNewPassword, updateUser.getSalt()));
        baseMapper.updateById(updateUser);
    }

    /**
     * 根据用户名获取用户 信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    public SysUser getUserByUsername(String userName) {
        return getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userName), false);
    }

    /**
     * 根据邮箱获取用户 信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    public SysUser getUserByEmail(String email) {
        return getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getEmail, email), false);
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return BaseUserInfo
     */
    public BaseUserInfo getUserInfo(String userId) {
        SysUser sysUser = baseMapper.selectById(userId);
        if (ObjectUtil.isNull(sysUser)) {
            return new BaseUserInfo();
        }

        return new BaseUserInfo().convertBySysUser(sysUser);
    }

    /**
     * 获取用户map <id, BaseUserInfo>
     *
     * @param userIds 用户id集合
     */
    public Map<String, BaseUserInfo> getBaseUserInfoMap(List<String> userIds) {
        List<SysUser> sysUsers = listByIds(userIds);
        if (CollUtil.isEmpty(sysUsers)) {
            return new HashMap<>();
        }
        return sysUsers.stream()
                .map(sysUser -> new BaseUserInfo().convertBySysUser(sysUser))
                .collect(Collectors.toMap(BaseUserInfo::getId, x -> x));
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    public SysUser selectByUsername(String username) {
        return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    }

}

