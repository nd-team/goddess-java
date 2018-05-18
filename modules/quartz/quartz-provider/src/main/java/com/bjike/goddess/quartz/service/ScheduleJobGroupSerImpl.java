package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import com.bjike.goddess.quartz.entity.ScheduleJobGroup;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.to.UserTO;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class ScheduleJobGroupSerImpl extends ServiceImpl<ScheduleJobGroup, ScheduleJobGroupDTO> implements ScheduleJobGroupSer {
    @Autowired
    private ScheduleJobSer scheduleJobSer;
    @Autowired
    private ScheduleSer scheduleSer;


    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "addConfirm", cancelMethod = "addCancel")
    @Override
    public ScheduleJobGroupBO add(TransactionContext txContext, ScheduleJobGroupTO jobGroupTO) throws SerException {
        ScheduleJobGroup jobGroup = BeanTransform.copyProperties(jobGroupTO, ScheduleJobGroup.class);
        super.save(jobGroup);
        return BeanTransform.copyProperties(jobGroup, ScheduleJobGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    public String addConfirm(TransactionContext txContext, ScheduleJobGroupTO jobGroupTO) throws SerException {
        System.out.println("定时任务组添加");
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    public String addCancel(TransactionContext txContext, ScheduleJobGroupTO jobGroupTO) throws SerException {
        ScheduleJobGroupDTO dto = new ScheduleJobGroupDTO();
        dto.getConditions().add(Restrict.eq("name", jobGroupTO.getName()));
        ScheduleJobGroup group = super.findOne(dto);
        if(null!=group){
            super.remove(group);
        }
        return null;
    }


    @Override
    public void edit(ScheduleJobGroupTO jobGroupTO) throws SerException {
        ScheduleJobGroup jobGroup = super.findById(jobGroupTO.getId());
        BeanTransform.copyProperties(jobGroupTO, jobGroup);
        if (!jobGroupTO.getEnable()) {
            List<ScheduleJob> jobs = scheduleJobSer.findByGroupId(jobGroupTO.getId());
            for (ScheduleJob scheduleJob : jobs) {
                scheduleJob.setEnable(false);
                scheduleSer.stop(scheduleJob);
            }
            scheduleJobSer.update(jobs);
        }

        super.update(jobGroup);
    }

    @Override
    public void delete(String id) throws SerException {
        List<ScheduleJob> jobs = scheduleJobSer.findByGroupId(id);
        for (ScheduleJob scheduleJob : jobs) {
            scheduleSer.stop(scheduleJob);
        }
        super.remove(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        ScheduleJobGroup jobGroup = super.findById(id);
        if (null != jobGroup) {
            jobGroup.setEnable(enable);
            if (!enable) { //如果停用,则停用改组所有任务
                List<ScheduleJob> scheduleJobs = scheduleJobSer.findByGroupId(id);
                for (ScheduleJob scheduleJob : scheduleJobs) {
                    scheduleSer.stop(scheduleJob);
                    scheduleJob.setEnable(false);
                }
                scheduleJobSer.update(scheduleJobs);
            }

        } else {
            throw new SerException("该任务调度组不存在");
        }
        super.update(jobGroup);
    }
}