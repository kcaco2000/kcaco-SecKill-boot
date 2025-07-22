package top.kcaco.seckill.common.constant;

/**
 * Description: redis常量
 *
 * @author kcaco
 */
public interface RedisConstant {

    /**
     * 验证码
     */
    String VERIFICATION_CODE = "verification_code:";

    /**
     * 登录账户密码错误次数 redis key
     */
    String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

}
