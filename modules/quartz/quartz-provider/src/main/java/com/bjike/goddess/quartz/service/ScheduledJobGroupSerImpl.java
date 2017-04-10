package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.bo.ScheduledJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduledJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduledJobGroup;
import com.bjike.goddess.quartz.to.ScheduledJobGroupTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 任务调度组业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "quartzSerCache")
@Service
public class ScheduledJobGroupSerImpl extends ServiceImpl<ScheduledJobGroup, ScheduledJobGroupDTO> implements ScheduledJobGroupSer {
    @Override
    public ScheduledJobGroupBO add(ScheduledJobGroupTO jobGroupTO) throws SerException {
        ScheduledJobGroup jobGroup = BeanTransform.copyProperties(jobGroupTO, ScheduledJobGroup.class);
        super.save(jobGroup);
        return BeanTransform.copyProperties(jobGroup, ScheduledJobGroupBO.class);
    }

    @Override
    public void edit(ScheduledJobGroupTO jobGroupTO) throws SerException {
        ScheduledJobGroup jobGroup = super.findById(jobGroupTO.getId());
        BeanTransform.copyProperties(jobGroupTO, jobGroup);
        super.update(jobGroup);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        ScheduledJobGroup jobGroup = super.findById(id);
        if (null != jobGroup) {
            jobGroup.setEnable(enable);
        } else {
            throw new SerException("该任务调度组不存在");
        }
        super.update(jobGroup);
    }
}