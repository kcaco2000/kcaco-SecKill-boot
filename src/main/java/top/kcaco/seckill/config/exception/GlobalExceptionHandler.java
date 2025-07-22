package top.kcaco.seckill.config.exception;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.common.result.ResultCode;

/**
 * 全局异常处理
 *
 * @author kcaco
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public Result handlerNotLoginException(NotLoginException e) {
        log.error("未登录异常: {}", e.getMessage(), e);
        // 不同异常返回不同状态码
        String message = "";
        if (e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供Token";
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "Token无效";
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "登录信息已过期，请重新登录";
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "您的账户已在另一台设备上登录，如非本人操作，请立即修改密码";
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            message = "已被系统强制下线";
        } else {
            message = "请先登录再操作";
        }
        // 返回给前端
        return Result.failed(ResultCode.INVALID_TOKEN.getCode(), message);
    }

    /**
     * 超出最大上传限制
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("超出最大上传限制", e);
        return Result.failed("超出最大上传限制");
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRunTimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * 异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("异常: {}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

}
