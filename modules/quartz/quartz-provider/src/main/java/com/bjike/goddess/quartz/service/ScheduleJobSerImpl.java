package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.quartz.bo.ScheduleJobBO;
import com.bjike.goddess.quartz.dto.ScheduleJobDTO;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

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
public class ScheduleJobSerImpl extends ServiceImpl<ScheduleJob, ScheduleJobDTO> implements ScheduleJobSer {
    @Autowired
    private ScheduleSer scheduleSer;
    @Autowired
    private ScheduleJobGroupSer scheduleJobGroupSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public ScheduleJobBO add(ScheduleJobTO scheduleJobTO) throws SerException {
        ScheduleJob scheduleJob = BeanTransform.copyProperties(scheduleJobTO, ScheduleJob.class);
        scheduleJob.setUserId(userAPI.currentUser().getId());
        scheduleSer.verifyTrigger(scheduleJob);//验证执行方法是否正确
        this.verifySchedule(scheduleJob);
        ScheduleJobGroupDTO dto = new ScheduleJobGroupDTO();
        dto.getConditions().add(Restrict.eq("id", scheduleJobTO.getScheduleJobGroupId()));
        scheduleJob.setScheduleJobGroup(scheduleJobGroupSer.findOne(dto));
        super.save(scheduleJob);
        if (scheduleJob.getEnable()) {
            scheduleSer.start(scheduleJob);
        }
        return BeanTransform.copyProperties(scheduleJob, ScheduleJobBO.class);
    }

    @Override
    public void edit(ScheduleJobTO scheduleJobTO) throws SerException {
        ScheduleJob scheduleJob = super.findById(scheduleJobTO.getId());
        BeanTransform.copyProperties(scheduleJobTO, scheduleJob);
        this.verifySchedule(scheduleJob);
        scheduleSer.verifyTrigger(scheduleJob);//验证执行方法是否正确
        scheduleJob.setModifyTime(LocalDateTime.now());
        super.update(scheduleJob);
        if (scheduleJob.getEnable()) {
            scheduleSer.restart(scheduleJob);
        } else {
            scheduleSer.stop(scheduleJob);
        }


    }

    @Override
    public void delete(String id) throws SerException {
        ScheduleJobDTO dto = new ScheduleJobDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        ScheduleJob scheduleJob = super.findOne(dto);
        scheduleSer.stop(scheduleJob);
        super.remove(id);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        ScheduleJob scheduleJob = super.findById(id);
        if (null != scheduleJob) {
            if (enable) {
                scheduleSer.restart(scheduleJob);
            } else {
                scheduleSer.stop(scheduleJob);
            }
            scheduleJob.setEnable(enable);
        } else {
            throw new SerException("该任务调度不存在");
        }
        super.update(scheduleJob);
    }

    @Override
    public List<ScheduleJob> findScheduleJobs() throws SerException {
        ScheduleJobDTO dto = new ScheduleJobDTO();
        dto.getConditions().add(Restrict.eq("scheduleJobGroup.enable", 0));
        dto.getConditions().add(Restrict.eq("enable", 0));
        return findByCis(dto);
    }

    @Override
    public List<ScheduleJob> findByGroupId(String id) throws SerException {
        ScheduleJobDTO dto = new ScheduleJobDTO();
        dto.getConditions().add(Restrict.eq("scheduleJobGroup.id", id));
        return findByCis(dto);
    }

    private void verifySchedule(ScheduleJob scheduleJob) throws SerException {
        Class<?> clazz = classExists(scheduleJob.getClazz());
        if (clazz != null) {
            Method method = methodExists(clazz, scheduleJob.getMethod());
            if (null != method) {
                if (!parameterTypesExists(method)) {
                    throw new SerException("执行方法中不能存有任何参数");
                }
            } else {
                throw new SerException("执行方法不存在此调用类中");
            }
        } else {
            throw new SerException("调用类不存在此系统中");
        }
    }


    private Class<?> classExists(String className) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (null == classLoader) classLoader = ScheduleJobSerImpl.class.getClassLoader();
            return classLoader.loadClass(className);
        } catch (Exception e) {
            return null;
        }
    }

    private Method methodExists(Class<?> clazz, String methodName) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }
            return null;
        } catch (SecurityException e) {
            return null;
        }
    }

    public boolean parameterTypesExists(Method method) {
        try {
            Class<?>[] cc = method.getParameterTypes();
            if (0 == cc.length) {
                return true;
            }
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

}