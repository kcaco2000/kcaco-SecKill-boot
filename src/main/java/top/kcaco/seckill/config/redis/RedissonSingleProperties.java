package top.kcaco.seckill.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: redisson单机配置属性
 *
 * @author kcaco
 */
@Data
@Component
@ConfigurationProperties(prefix = "redisson.single-server-config")
public class RedissonSingleProperties {

    /**
     * 数据库
     */
    private Integer database;

    /**
     * 地址
     */
    private String address;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接超时时间
     */
    private Integer connectTimeout;

    /**
     * 响应超时时间
     */
    private Integer timeout;
}
