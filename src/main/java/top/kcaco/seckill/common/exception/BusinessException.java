package top.kcaco.seckill.common.exception;

import lombok.Getter;

/**
 * Description: 业务异常
 *
 * @author kcaco
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误代码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    public BusinessException() {
        this.code = ExceptionCodeEnum.SERVER_ERROR.getCode();
        this.message = ExceptionCodeEnum.SERVER_ERROR.getDesc();
    }

    public BusinessException(String message) {
        this.code = ExceptionCodeEnum.SERVER_ERROR.getCode();
        this.message = message;
    }


}
