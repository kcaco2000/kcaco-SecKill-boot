package top.kcaco.seckill.modules.sysuser.domain.request;


import lombok.Data;

@Data
public class UpdateProfileInfoReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人说明
     */
    private String info;

}
