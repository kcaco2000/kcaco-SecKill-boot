package top.kcaco.seckill.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kcaco
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private RedissonSingleProperties redissonSingleProperties;

    /**
     * 单机
     * <p>
     * 服务停止后调用 shutdown 方法
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        // 1.创建配置
        Config config = new Config();

        // 2.根据 Config 创建出 RedissonClient 实例
        config.useSingleServer()
                .setAddress(redissonSingleProperties.getAddress())
                .setPassword(redissonSingleProperties.getPassword())
                .setDatabase(redissonSingleProperties.getDatabase())
                .setConnectTimeout(redissonSingleProperties.getConnectTimeout())
                // 连接池最小空闲连接数
                //.setConnectionMinimumIdleSize()
                // 连接池最大线程数
                //.setConnectionPoolSize()
                // 线程超时时间
                //.setIdleConnectionTimeout()
                .setTimeout(redissonSingleProperties.getTimeout());

        // 集群模式
        // config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");

        return Redisson.create(config);
    }


}
