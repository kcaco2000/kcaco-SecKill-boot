package top.kcaco.seckill.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.kcaco.seckill.common.exception.ExceptionCodeEnum;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author kcaco
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 带消息成功返回
     */
    public static Result successWithMsg(String msg) {
        return new Result(ResultCode.SUCCESS.getCode(), msg, null);
    }

    /**
     * 带数据成功返回
     */
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 不带数据成功返回
     */
    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 错误返回，指定错误消息
     */
    public static Result failed(String msg) {
        return new Result(ResultCode.FAILED.getCode(), msg, null);
    }

    /**
     * 错误返回，指定错误消息
     */
    public static Result failed(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 错误返回，默认错误消息
     */
    public static Result failed() {
        return failed(ResultCode.FAILED.getMessage());
    }


    public static Result failed(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result failed(ExceptionCodeEnum exceptionCodeEnum) {
        return new Result(exceptionCodeEnum.getCode(), exceptionCodeEnum.getDesc());
    }


}
