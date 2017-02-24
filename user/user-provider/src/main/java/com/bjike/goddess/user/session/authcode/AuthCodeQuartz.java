package com.bjike.goddess.user.session.authcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证码定时器
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-25 17:41]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthCodeQuartz {
    private static final Logger CONSOLE = LoggerFactory.getLogger(AuthCodeQuartz.class);
    /**
     * session key失效时间 3分钟
     */
    private final static int INVALID_TIME = 3;
    /**
     * 设置执行开始时间
     */
    private final static int START = 0;
    /**
     * 设置间隔执行时间 单位/毫秒
     */
    private final static int INTERVAL = 5000;
    private static Map<String, AuthCode> authCodeSession = new ConcurrentHashMap<>(0);

    public AuthCodeQuartz(Map<String, AuthCode> authCodeSession) {
        this.authCodeSession = authCodeSession;
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, AuthCode> entry : authCodeSession.entrySet()) {
                    if (entry.getValue().getCreateTime().plusMinutes(INVALID_TIME).isBefore(LocalDateTime.now())) {
                        CONSOLE.info("remove authCode:" + entry.getKey());
                        authCodeSession.remove(entry.getKey());
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔10秒执行一次

    }

    public static Map<String, AuthCode> session() {
        return authCodeSession;
    }

}
