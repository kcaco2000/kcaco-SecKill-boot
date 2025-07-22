package top.kcaco.seckill.modules.sysuser.domain.request;


import lombok.Data;

@Data
public class UpdatePasswordReq {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
