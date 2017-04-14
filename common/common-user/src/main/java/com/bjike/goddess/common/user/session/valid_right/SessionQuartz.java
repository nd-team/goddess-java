package com.bjike.goddess.common.user.session.valid_right;

import com.bjike.goddess.common.user.session.constant.UserCommon;

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

    /**
     * 设置执行开始时间
     */
    private final static int START = 0;
    /**
     * 设置间隔执行时间 单位/毫秒
     */
    private final static int INTERVAL = 3000;
    private Map<String, LoginUser> user_session; // token LoginUser

    public SessionQuartz( Map<String, LoginUser> user_session) {
        this.user_session = user_session;
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, LoginUser> entry : user_session.entrySet()) {
                    if (entry.getValue().getAccessTime().plusMinutes(UserCommon.LOGIN_TIMEOUT).isBefore(LocalDateTime.now())) {
                        System.out.println("remove token:" + entry.getKey());
                        user_session.remove(entry.getValue()); //通过token移除用户
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔3秒执行一次
    }


}
