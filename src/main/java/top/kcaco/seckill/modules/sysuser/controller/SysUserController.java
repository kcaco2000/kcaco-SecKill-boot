package top.kcaco.seckill.modules.sysuser.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginUser;
import top.kcaco.seckill.modules.sysuser.domain.request.UpdatePasswordReq;
import top.kcaco.seckill.modules.sysuser.domain.request.UpdateProfileInfoReq;
import top.kcaco.seckill.modules.sysuser.service.SysUserService;

/**
 * 用户(SysUser)表控制层
 *
 * @author kcaco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sysUser")
public class SysUserController {

    private final SysUserService sysUserService;

    /**
     * 获取登录用户信息
     *
     * @return 修改结果
     */
    @GetMapping("/info")
    public Result info() {
        LoginUser loginUser = sysUserService.loginUserInfo();
        return Result.success(loginUser);
    }

    /**
     * 更新个人信息
     *
     * @param updateProfileInfoReq 实体对象
     * @return 修改结果
     */
    @PutMapping("updateProfileInfo")
    public Result updateProfileInfo(HttpServletRequest request, @RequestBody UpdateProfileInfoReq updateProfileInfoReq) {
        return Result.success(sysUserService.updateProfileInfo(request, updateProfileInfoReq));
    }

    /**
     * 修改密码
     *
     * @param updatePasswordReq 实体对象
     * @return 修改结果
     */
    @PutMapping("updatePassword")
    public Result updatePassword(@RequestBody UpdatePasswordReq updatePasswordReq) {
        sysUserService.updatePassword(updatePasswordReq);
        return Result.success();
    }


}

