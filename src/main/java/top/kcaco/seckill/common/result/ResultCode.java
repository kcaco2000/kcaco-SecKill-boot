package top.kcaco.seckill.common.result;

import lombok.Getter;

/**
 * 返回结果枚举
 **/
@Getter
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 失败
     */
    FAILED(500, "失败"),

    /**
     * 数据错误
     */
    DATA_FAILED(400, "数据错误"),

    /**
     * 登录失败
     */
    LOGIN_FAILED(401, "用户名或密码错误"),

    /**
     * 没有认证信息
     */
    NO_TOKEN(403, "请重新登录"),

    /**
     * 认证失败
     */
    AUTH_FAILED(403, "认证失败"),

    /**
     * token过期
     */
    TOKEN_EXPIRE(403, "登录超时，请重新登录!"),

    /**
     * 无效token
     */
    INVALID_TOKEN(403, "无效的令牌，请重新登录后访问!"),

    /**
     * 没有权限
     */
    NO_AUTH(999, "没有访问权限")

    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
