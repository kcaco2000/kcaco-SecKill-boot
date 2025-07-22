package top.kcaco.seckill.utils;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.common.result.ResultCode;

import java.io.IOException;

/**
 * Description: 客户端工具类
 *
 * @author kcaco
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletUtil extends JakartaServletUtil {

    /**
     * 从RequestContextHolder中获取ServletRequestAttributes
     *
     * @return {@link ServletRequestAttributes} ServletRequestAttributes对象
     * @throws IllegalStateException 如果获取不到RequestAttributes
     */
    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("当前请求上下文中不存在RequestAttributes");
        }
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取当前请求的HttpServletResponse对象
     *
     * @return {@link HttpServletResponse} 当前请求的HttpServletResponse对象
     * @throws IllegalStateException 如果当前上下文中没有RequestAttributes
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取客户端ip
     *
     * @return ip地址
     */
    public static String getClientIP() {
        return getClientIP(getRequest());
    }


    public static void returnString(HttpServletResponse response, Integer statusCode, String errorMessage) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("utf-8");

            // 错误信息
            Result result = Result.failed(statusCode, errorMessage);
            response.getWriter().println(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void returnString(HttpServletResponse response, ResultCode resultCode) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("utf-8");

            // 错误信息
            Result result = Result.failed(resultCode.getCode(), resultCode.getMessage());
            response.getWriter().println(JSONUtil.toJsonStr(result));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
