package com.bjike.goddess.user.session.validfail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-25 17:41]
 * @Description: [验证密码错误次数定时器]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ValidErrQuartz {
    private static final Logger CONSOLE = LoggerFactory.getLogger(ValidErrQuartz.class);
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
    private Map<String, ValidErr> sessions;

    public ValidErrQuartz(Map<String, ValidErr> sessions) {
        this.sessions = sessions;
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, ValidErr> entry : sessions.entrySet()) {
                    if (entry.getValue().getCreateTime().plusMinutes(INVALID_TIME).isBefore(LocalDateTime.now())) {
                        CONSOLE.info("remove account:" + entry.getKey());
                        sessions.remove(entry.getKey());
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔10秒执行一次

    }

}
