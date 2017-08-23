package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import org.quartz.CronScheduleBuilder;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-10 11:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ScheduleSer {


    default CronScheduleBuilder verifyTrigger(ScheduleJob scheduleJob) throws SerException {
        return null;
    }

    default List<ScheduleJob> jobs() throws SerException {
        return null;
    }

    default void start(ScheduleJob scheduleJob) throws SerException {


    }

    default void stop(ScheduleJob scheduleJob) throws SerException {

    }

    default void restart(ScheduleJob scheduledJob) throws SerException {

    }

    default void del(ScheduleJob scheduledJob) throws SerException {


    }
}
