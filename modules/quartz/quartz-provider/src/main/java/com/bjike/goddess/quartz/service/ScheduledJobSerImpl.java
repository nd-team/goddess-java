package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.bo.ScheduledJobBO;
import com.bjike.goddess.quartz.dto.ScheduledJobDTO;
import com.bjike.goddess.quartz.entity.ScheduledJob;
import com.bjike.goddess.quartz.to.ScheduledJobTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 任务调度业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "quartzSerCache")
@Service
public class ScheduledJobSerImpl extends ServiceImpl<ScheduledJob, ScheduledJobDTO> implements ScheduledJobSer {
    @Override
    public ScheduledJobBO add(ScheduledJobTO scheduledJobTO) throws SerException {
        ScheduledJob scheduledJob = BeanTransform.copyProperties(scheduledJobTO, ScheduledJob.class);
        super.save(scheduledJob);
        return BeanTransform.copyProperties(scheduledJob, ScheduledJobBO.class);
    }

    @Override
    public void edit(ScheduledJobTO scheduledJobTO) throws SerException {
        ScheduledJob scheduledJob = super.findById(scheduledJobTO.getId());
        BeanTransform.copyProperties(scheduledJobTO, scheduledJob);
        super.update(scheduledJob);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        ScheduledJob scheduledJob = super.findById(id);
        if (null != scheduledJob) {
            scheduledJob.setEnable(enable);
        } else {
            throw new SerException("该任务调度组不存在");
        }
        super.update(scheduledJob);
    }
}