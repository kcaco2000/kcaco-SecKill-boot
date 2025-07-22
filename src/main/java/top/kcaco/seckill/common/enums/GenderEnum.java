package top.kcaco.seckill.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 性别枚举
 *
 * @author kcaco
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    MAN("man", "男"),
    WO_MAN("wo_man", "女"),
    UN_KNOW("un_know", "未知"),
    ;

    private final String code;
    private final String message;


    /**
     * 根据代码获取枚举
     *
     * @param code 代码
     * @return 枚举
     */
    public static GenderEnum getByCode(String code) {
        if (StrUtil.isBlank(code)) {
            throw new RuntimeException("代码为空，请检查");
        }
        for (GenderEnum value : GenderEnum.values()) {
            if (StrUtil.equals(code, value.getCode())) {
                return value;
            }
        }
        throw new RuntimeException("无效的性别代码" + code);
    }
}
