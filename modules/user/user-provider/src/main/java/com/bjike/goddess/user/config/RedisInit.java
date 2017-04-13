package com.bjike.goddess.user.config;

import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.quartz.UserQuartz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 11:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class RedisInit {
    @Autowired
    private RedisClient client;
    @PostConstruct
    public void init(){
        new UserQuartz(client);
        System.out.println("session_to_redis start");
    }
}
