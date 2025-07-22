package top.kcaco.seckill.modules.sysuser.domain.model;

import lombok.Data;

/**
 * 登录返回体
 *
 * @author kcaco
 */
@Data
public class LoginResult {

    /**
     * token
     */
    private String accessToken;

}
