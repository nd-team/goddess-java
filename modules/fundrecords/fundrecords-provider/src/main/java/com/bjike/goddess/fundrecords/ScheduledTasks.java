package com.bjike.goddess.fundrecords;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.fundrecords.service.CapitalFlowRecordSer;
import com.bjike.goddess.user.api.UserLoginAPI;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.to.UserLoginTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-26 09:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    CapitalFlowRecordSer capitalFlowRecordSer;
    @Autowired
    UserLoginAPI userLoginAPI;

    @Scheduled(cron = "00 00 0 ? * *")   //每天00:00:00开始执行
//    @Scheduled(fixedRate = 43200000)
    public void reportCurrentTime() throws SerException {
        //todo
        UserLoginTO to = new UserLoginTO();
        to.setAccount("admin");
        to.setPassword("123456");//abc123
        to.setIp("127.0.0.1");
        to.setLoginType(LoginType.NONE);
        String token = userLoginAPI.login(to);

        System.out.println("定时任务启动：The time is now {}" + dateFormat.format(new Date()));

        System.out.println("更新资金流水表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        capitalFlowRecordSer.listRecordTask();
        System.out.println("更新资金流水表结束：" + dateFormat.format(new Date()));

        System.out.println("定时任务结束：The time is now {}" + dateFormat.format(new Date()));

    }
}
