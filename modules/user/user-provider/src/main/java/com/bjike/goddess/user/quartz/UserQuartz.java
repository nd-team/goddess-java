package com.bjike.goddess.user.quartz;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.user.session.constant.UserCommon;
import com.bjike.goddess.common.user.session.valid_right.LoginUser;
import com.bjike.goddess.common.user.session.valid_right.UserSession;
import com.bjike.goddess.redis.client.RedisClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 10:57]
 * @Description: [定时保存用户session到redis ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserQuartz {
    private RedisClient client;

    public UserQuartz(RedisClient client) {
        this.client = client;
        startTimer();
    }

    /**
     * 设置执行开始时间
     */
    private final static int START = 1000 * 60;

    /**
     * 每3秒执行
     */
    private final static int INTERVAL = 1000 * 60;


    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                Map<String, LoginUser> map = UserSession.sessions();
                if (null != map) {
                    Map<String, String> login_users = new HashMap<>(map.size());
                    Map<String, String> userId_tokens = new HashMap<>(map.size());
                    for (Map.Entry<String, LoginUser> entry : map.entrySet()) {
                        //保存登录用户  用户id - 登录用户
                        login_users.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
                        userId_tokens.put(entry.getValue().getId(), entry.getKey());
                    }
                    try {
                        client.saveMap(UserCommon.LOGIN_USER, login_users, UserCommon.LOGIN_TIMEOUT);
                        client.saveMap(UserCommon.USERID_TOKEN, userId_tokens, UserCommon.LOGIN_TIMEOUT);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }

            }
        }, START, INTERVAL);//从0秒开始，每隔10秒执行一次
    }

}
