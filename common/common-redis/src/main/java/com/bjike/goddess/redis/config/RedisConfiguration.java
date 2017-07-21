package com.bjike.goddess.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-18 10:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Configuration
public class RedisConfiguration {

    /**
     * 配置连接池
     *
     * @param env
     * @return
     */
    @Autowired
    @Bean(name = "jedisPool")
    public JedisPool jedisPool(Environment env) throws InterruptedException{
        JedisPoolConfig config = new JedisPoolConfig();
        String host = env.getProperty("redis.host");
        int post = Integer.parseInt(env.getProperty("redis.post"));
        int maxTotal = Integer.parseInt(env.getProperty("redis.pool.maxTotal"));
        int maxIdle = Integer.parseInt(env.getProperty("redis.pool.maxIdle"));
        int maxWaitMillis = Integer.parseInt(env.getProperty("redis.pool.maxWaitMillis"));
        long timeout = Long.parseLong(env.getProperty("redis.timeout"));
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.wait(timeout);
        return new JedisPool(config, host, post);
    }

}
