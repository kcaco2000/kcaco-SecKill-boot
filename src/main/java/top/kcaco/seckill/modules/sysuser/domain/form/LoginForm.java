package top.kcaco.seckill.modules.sysuser.domain.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kcaco
 */
@Data
public class LoginForm implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
