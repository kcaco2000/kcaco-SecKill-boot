package top.kcaco.seckill.modules.sysuser.domain.model;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.useragent.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息载体
 *
 * @author kcaco
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginUser extends BaseUserInfo implements Serializable {

    /**
     * 过期时间
     */
    private Long expireTime;

    public void setBaseUserInfo(BaseUserInfo baseUserInfo) {
        this.setId(baseUserInfo.getId());
        this.setAvatar(baseUserInfo.getAvatar());
        this.setUsername(baseUserInfo.getUsername());
        this.setNickname(baseUserInfo.getNickname());
        this.setGender(baseUserInfo.getGender());
        this.setEmail(baseUserInfo.getEmail());
        this.setInfo(baseUserInfo.getInfo());
        this.setStatus(baseUserInfo.getStatus());
        this.setRole(baseUserInfo.getRole());
    }

    public LoginUser(BaseUserInfo baseUserInfo, HttpServletRequest request) {
        this.setBaseUserInfo(baseUserInfo);

        // 过期时间
        this.setExpireTime(StpUtil.getTokenTimeout());
    }
}
