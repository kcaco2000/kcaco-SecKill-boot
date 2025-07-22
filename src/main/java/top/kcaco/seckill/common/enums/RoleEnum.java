package top.kcaco.seckill.common.enums;


import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 用户角色枚举
 *
 * @author kcaco
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN("admin", "管理员"),
    USER("user", "用户");

    private final String code;
    private final String message;

    /**
     * 根据代码获取枚举
     */
    public static RoleEnum getByCode(String code) {
        if (StrUtil.isBlank(code)) {
            throw new RuntimeException("代码为空，请检查");
        }
        for (RoleEnum value : RoleEnum.values()) {
            if (StrUtil.equals(code, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("无效的角色码：" + code);
    }
}
