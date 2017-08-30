package com.bjike.goddess.redis.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-29 11:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RedisScan {
    @Autowired
    Environment env;

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        int maxTotal = Integer.parseInt(env.getProperty("redis.pool.maxTotal"));
        int maxIdle = Integer.parseInt(env.getProperty("redis.pool.maxIdle"));
        int maxWaitMillis = Integer.parseInt(env.getProperty("redis.pool.maxWaitMillis"));
        long timeout = Long.parseLong(env.getProperty("redis.timeout"));
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        try {
            config.wait(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return config;
    }


    @Bean
    public JedisPool jedisPools(JedisPoolConfig config) throws InterruptedException {
        String host = env.getProperty("redis.host");
        int port = Integer.parseInt(env.getProperty("redis.port"));
        return new JedisPool(config, host, port);
    }
}
