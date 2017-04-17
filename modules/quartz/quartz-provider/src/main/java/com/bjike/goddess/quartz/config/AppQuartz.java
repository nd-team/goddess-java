package com.bjike.goddess.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 配置项扫描
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
//@Component
public class AppQuartz {


    public SchedulerFactoryBean getSchedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setStartupDelay(5);
//        ScheduleSerImpl.schedulerFactoryBean = schedulerFactoryBean;
        return schedulerFactoryBean;
    }


}
