package com.bjike.goddess.redis.config;

import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.redis.client.RedisClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-18 10:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Configuration
@ConditionalOnClass(RedisClient.class)
public class JedisAutoConfiguration {


    /**
     * 配置连接池
     *
     * @param env
     * @return
     */
    @Bean(name = "jedisPool")
    public JedisPool jedisPool(Environment env) {
        JedisPoolConfig config = new JedisPoolConfig();
        String host = env.getProperty("redis.host");
        int post = Integer.parseInt(env.getProperty("redis.post"));
        int maxTotal = Integer.parseInt(env.getProperty("redis.pool.maxTotal"));
        int maxIdle = Integer.parseInt(env.getProperty("redis.pool.maxIdle"));
        int maxWaitMillis = Integer.parseInt(env.getProperty("redis.pool.maxWaitMillis"));
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return new JedisPool(config, host, post);
    }

    /**
     * 容器中如果没有RedisClient这个类,那么自动配置这个RedisClient
     *
     * @param pool
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
        RedisClient redisClient = new RedisClientImpl();
        redisClient.setJedisPool(pool);
        return redisClient;
    }


}
