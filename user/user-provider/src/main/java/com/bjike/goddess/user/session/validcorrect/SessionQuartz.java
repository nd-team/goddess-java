package com.bjike.goddess.user.session.validcorrect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 用户登陆session保存
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SessionQuartz {

    private static final Logger CONSOLE = LoggerFactory.getLogger(SessionQuartz.class);
    /**
     * session key失效时间 1天
     */
    private final static int INVALID_TIME = 60 * 24;
    /**
     * 设置执行开始时间
     */
    private final static int START = 0;
    /**
     * 设置间隔执行时间 单位/毫秒
     */
    private final static int INTERVAL = 3000;
    private Map<String, Subject> sessions;

    public SessionQuartz(Map<String, Subject> sessions) {
        this.sessions = sessions;
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, Subject> entry : sessions.entrySet()) {
                    if (entry.getValue().getAccessTime().plusMinutes(INVALID_TIME).isBefore(LocalDateTime.now())) {
                        CONSOLE.info("remove token:" + entry.getKey());
                        sessions.remove(entry.getKey());
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔10秒执行一次

    }


}
