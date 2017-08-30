package com.bjike.goddess.user.config;

import com.bjike.goddess.redis.client.RedisClientImpl;
import com.bjike.goddess.redis.client.RedisScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * 扫描Redis
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-11 17:15]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppRedisScan extends RedisScan {


}
