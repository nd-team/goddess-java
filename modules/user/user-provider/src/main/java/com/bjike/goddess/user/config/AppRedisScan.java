package com.bjike.goddess.user.config;

import com.bjike.goddess.redis.client.RedisClientImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

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
public class AppRedisScan extends RedisClientImpl {

}
