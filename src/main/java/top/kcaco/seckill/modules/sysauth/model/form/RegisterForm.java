package top.kcaco.seckill.modules.sysauth.model.form;

import lombok.Data;


/**
 * Description: 用户注册信息
 *
 * @author kcaco
 */
@Data
public class RegisterForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String code;

}
