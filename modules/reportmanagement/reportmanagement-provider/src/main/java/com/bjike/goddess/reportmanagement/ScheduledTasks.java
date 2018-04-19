package com.bjike.goddess.reportmanagement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.reportmanagement.service.*;
import com.bjike.goddess.user.api.UserLoginAPI;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.to.UserLoginTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 报表定时任务
 *
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
    ProfitSer profitSer;
    @Autowired
    AssetSer assetSer;
    @Autowired
    DebtSer debtSer;
    @Autowired
    CashFlowDataSer cashFlowDataSer;
    @Autowired
    CashFlowProjectSer cashFlowProjectSer;
    @Autowired
    UserLoginAPI userLoginAPI;

    @Scheduled(cron = "0 30 0 ? * *")   //每天00:30:00开始执行
//    @Scheduled(fixedRate = 43200000)
    public void reportCurrentTime() throws SerException {


        System.out.println("定时任务启动：The time is now {}" + dateFormat.format(new Date()));

        UserLoginTO userLoginTO = new UserLoginTO();
        userLoginTO.setAccount("admin");
        userLoginTO.setPassword("abc123");
        userLoginTO.setIp("127.0.0.1");
        userLoginTO.setLoginType(LoginType.NONE);
        String token = userLoginAPI.login(userLoginTO);
        System.out.println("更新资产表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        assetSer.assetTask();
        System.out.println("更新资产表结束：" + dateFormat.format(new Date()));

        System.out.println("更新负债表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        debtSer.assetDebtTask();
        System.out.println("更新负债表结束：" + dateFormat.format(new Date()));

        System.out.println("更新现金流量表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        cashFlowProjectSer.cashFlowProjectTask();
        System.out.println("更新现金流量表结束：" + dateFormat.format(new Date()));

        System.out.println("更新现金流量补充资料表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        cashFlowDataSer.cashFlowDataTask();
        System.out.println("更新现金流量补充资料表结束：" + dateFormat.format(new Date()));

        System.out.println("更新利润表开始：" + dateFormat.format(new Date()));
        RpcTransmit.transmitUserToken(token);
        profitSer.profitTask();
        System.out.println("更新利润表结束：" + dateFormat.format(new Date()));

        System.out.println("定时任务结束：The time is now {}" + dateFormat.format(new Date()));

    }
}
