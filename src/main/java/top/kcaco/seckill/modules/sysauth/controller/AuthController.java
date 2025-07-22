package top.kcaco.seckill.modules.sysauth.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.modules.sysauth.service.AuthService;
import top.kcaco.seckill.modules.sysuser.domain.form.LoginForm;
import top.kcaco.seckill.modules.sysuser.domain.model.LoginResult;


/**
 * Description: 认证控制
 *
 * @author kcaco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * 用户登录
     */
    @SaIgnore
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginForm loginForm) {
        LoginResult loginResult = authService.login(loginForm);
        return Result.success(loginResult);
    }

    // /**
    //  * 用户注册
    //  */
    // @SaIgnore
    // @PostMapping("/register")
    // public Result register(@RequestBody RegisterDto registerDto) {
    //     return authService.register(registerDto);
    // }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout() {
        return authService.logout();
    }


}
