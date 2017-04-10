package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduleJobBO;
import com.bjike.goddess.quartz.service.ScheduleJobSer;
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
    private ScheduleJobSer scheduleJobSer;

    @Override
    public ScheduleJobBO add(ScheduleJobTO scheduledJobTO) throws SerException {
        return scheduleJobSer.add(scheduledJobTO);
    }

    @Override
    public void edit(ScheduleJobTO scheduledJobTO) throws SerException {
        scheduleJobSer.edit(scheduledJobTO);
    }

    @Override
    public void delete(String id) throws SerException {
        scheduleJobSer.delete(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        scheduleJobSer.enable(id, enable);
    }
}