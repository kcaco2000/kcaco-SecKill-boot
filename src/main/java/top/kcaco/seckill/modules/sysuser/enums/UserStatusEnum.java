package top.kcaco.seckill.modules.sysuser.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 用户状态
 *
 * @author kcaco
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    DISABLED("disabled", "禁用"),
    NORMAL("normal", "正常");


    private final String code;
    private final String message;

    /**
     * 根据代码获取枚举
     *
     * @param code 代码
     * @return 枚举
     */
    public static UserStatusEnum getByCode(String code) {
        if (StrUtil.isBlank(code)) {
            throw new RuntimeException("代码为空，请检查");
        }
        for (UserStatusEnum value : UserStatusEnum.values()) {
            if (StrUtil.equals(code, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("无效的用户状态：" + code);
    }

    /**
     * 根据代码获取信息
     *
     * @param code 代码
     * @return 枚举
     */
    public static String getMessageByCode(Integer code) {
        if (ObjectUtil.isNull(code)) {
            return null;
        }
        for (UserStatusEnum value : UserStatusEnum.values()) {
            if (ObjectUtil.equals(code, value.getCode())) {
                return value.getMessage();
            }
        }
        return null;
    }
}
