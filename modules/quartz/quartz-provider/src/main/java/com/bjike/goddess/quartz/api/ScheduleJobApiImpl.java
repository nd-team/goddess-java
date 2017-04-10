package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduleJobBO;
import com.bjike.goddess.quartz.service.ScheduledJobSer;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务调度业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("scheduleJobApiImpl")
public class ScheduleJobApiImpl implements ScheduleJobAPI {
    @Autowired
    private ScheduledJobSer scheduledJobSer;

    @Override
    public ScheduleJobBO add(ScheduleJobTO scheduledJobTO) throws SerException {
        return scheduledJobSer.add(scheduledJobTO);
    }

    @Override
    public void edit(ScheduleJobTO scheduledJobTO) throws SerException {
        scheduledJobSer.edit(scheduledJobTO);
    }

    @Override
    public void delete(String id) throws SerException {
        scheduledJobSer.delete(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        scheduledJobSer.enable(id, enable);
    }
}