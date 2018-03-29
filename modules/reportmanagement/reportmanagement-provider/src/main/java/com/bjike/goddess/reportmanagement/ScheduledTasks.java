package com.bjike.goddess.reportmanagement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    ProfitSer profitSer;
    @Autowired
    AssetSer assetSer;
    @Autowired
    DebtSer debtSer;
    @Autowired
    CashFlowDataSer cashFlowDataSer;
    @Autowired
    CashFlowProjectSer cashFlowProjectSer;

    @Scheduled(cron = "0 30 0 ? * *")   //每天00:30:00开始执行
//    @Scheduled(fixedRate = 43200000)
    public void reportCurrentTime() throws SerException {


        System.out.println("定时任务启动：The time is now {}" + dateFormat.format(new Date()));

        System.out.println("更新资产表开始：" + dateFormat.format(new Date()));
        assetSer.assetTask();
        System.out.println("更新资产表结束：" + dateFormat.format(new Date()));

        System.out.println("更新负债表开始：" + dateFormat.format(new Date()));
        debtSer.assetDebtTask();
        System.out.println("更新负债表结束：" + dateFormat.format(new Date()));

        System.out.println("更新现金流量表开始：" + dateFormat.format(new Date()));
        cashFlowProjectSer.cashFlowProjectTask();
        System.out.println("更新现金流量表结束：" + dateFormat.format(new Date()));

        System.out.println("更新现金流量补充资料表开始：" + dateFormat.format(new Date()));
        cashFlowDataSer.cashFlowDataTask();
        System.out.println("更新现金流量补充资料表结束：" + dateFormat.format(new Date()));

        System.out.println("更新利润表开始：" + dateFormat.format(new Date()));
        profitSer.profitTask();
        System.out.println("更新利润表结束：" + dateFormat.format(new Date()));

        System.out.println("定时任务结束：The time is now {}" + dateFormat.format(new Date()));

    }
}
