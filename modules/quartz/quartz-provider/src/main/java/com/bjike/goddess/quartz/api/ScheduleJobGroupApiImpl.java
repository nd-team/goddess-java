package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.service.ScheduleJobGroupSer;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class ScheduleJobGroupApiImpl implements ScheduleJobGroupAPI {
    @Autowired
    private ScheduleJobGroupSer scheduleJobGroupSer;

    @Override
    public ScheduleJobGroupBO add(TransactionContext context,ScheduleJobGroupTO jobGroupTO) throws SerException {
        return scheduleJobGroupSer.add(context  , jobGroupTO);
    }

    @Override
    public void edit(ScheduleJobGroupTO jobGroupTO) throws SerException {
        scheduleJobGroupSer.edit(jobGroupTO);
    }

    @Override
    public void delete(String id) throws SerException {
        scheduleJobGroupSer.delete(id);

    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        scheduleJobGroupSer.enable(id, enable);

    }

    @Override
    public List<ScheduleJobGroupBO> list(ScheduleJobGroupDTO dto) throws SerException {
        return BeanTransform.copyProperties(scheduleJobGroupSer.findByPage(dto),ScheduleJobGroupBO.class);
    }

    @Override
    public Long count(ScheduleJobGroupDTO dto) throws SerException {
        return scheduleJobGroupSer.count(dto);
    }

    @Override
    public ScheduleJobGroupBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(scheduleJobGroupSer.findById(id),ScheduleJobGroupBO.class);
    }

    @Override
    public List<ScheduleJobGroupBO> all(ScheduleJobGroupDTO dto) throws SerException {
        return BeanTransform.copyProperties(scheduleJobGroupSer.findByCis(dto),ScheduleJobGroupBO.class);
    }
}