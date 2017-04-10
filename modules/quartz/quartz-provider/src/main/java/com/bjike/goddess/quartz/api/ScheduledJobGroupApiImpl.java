package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.service.ScheduleJobGroupSer;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务调度组业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("scheduleJobGroupApiImpl")
public class ScheduledJobGroupApiImpl implements ScheduleJobGroupAPI {
    @Autowired
    private ScheduleJobGroupSer scheduledJobGroupSer;

    @Override
    public ScheduleJobGroupBO add(ScheduleJobGroupTO jobGroupTO) throws SerException {
        return scheduledJobGroupSer.add(jobGroupTO);
    }

    @Override
    public void edit(ScheduleJobGroupTO jobGroupTO) throws SerException {
        scheduledJobGroupSer.edit(jobGroupTO);
    }

    @Override
    public void delete(String id) throws SerException {
        scheduledJobGroupSer.delete(id);

    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        scheduledJobGroupSer.enable(id, enable);

    }
}