package com.bjike.goddess.user.config;

import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.session.valid_right.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-14 15:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class RedisInit {
    @Autowired
    private RedisClient redisClient;

    @PostConstruct
    public void init() {
        UserSession.redisClient = redisClient;
    }

}
