package com.bjike.goddess.common.user.session.valid_err;

import com.bjike.goddess.common.user.session.constant.UserCommon;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 密码错误定时器
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PwdErrQuartz {


    /**
     * 设置执行开始时间
     */
    private final static int START = 0;
    /**
     * 设置间隔执行时间 单位/毫秒
     */
    private final static int INTERVAL = 3000;
    private Map<String, PwdErr> pwd_err_session;

    public PwdErrQuartz(Map<String, PwdErr> pwd_err_session) {
        this.pwd_err_session = pwd_err_session;
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, PwdErr> entry : pwd_err_session.entrySet()) {
                    if (entry.getValue().getAccessTime().plusMinutes(UserCommon.ERR_CODE_TIMEOUT).isBefore(LocalDateTime.now())) {
                        System.out.println("remove account:" + entry.getKey());
                        pwd_err_session.remove(entry.getKey()); //通过account移除密码验证次数
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔3秒执行一次
    }


}
