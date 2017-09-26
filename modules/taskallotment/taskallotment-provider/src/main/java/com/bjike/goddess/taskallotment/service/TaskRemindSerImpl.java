package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.TaskRemindBO;
import com.bjike.goddess.taskallotment.dto.TaskRemindDTO;
import com.bjike.goddess.taskallotment.entity.TaskRemind;
import com.bjike.goddess.taskallotment.to.TaskRemindTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 任务提醒业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class TaskRemindSerImpl extends ServiceImpl<TaskRemind, TaskRemindDTO> implements TaskRemindSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TaskRemindBO save(TaskRemindTO to) throws SerException {
        TaskRemind entity = BeanTransform.copyProperties(to, TaskRemind.class, true);
        if (null != entity.getSecondTime()) {
            Long mis = entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getFirstTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第二次提醒时间不能小于第一次提醒时间");
            }
        }
        if (null != entity.getThridTime()) {
            if (null == entity.getSecondTime()) {
                throw new SerException("必须先填写第二次提醒时间");
            }
            Long mis = entity.getThridTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第三次提醒时间不能小于第二次提醒时间");
            }
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, TaskRemindBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TaskRemindTO to) throws SerException {
        TaskRemind entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, TaskRemind.class, true);
        entity.setCreateTime(a);
        if (null != entity.getSecondTime()) {
            Long mis = entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getFirstTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第二次提醒时间不能小于第一次提醒时间");
            }
        }
        if (null != entity.getThridTime()) {
            if (null == entity.getSecondTime()) {
                throw new SerException("必须先填写第二次提醒时间");
            }
            Long mis = entity.getThridTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第三次提醒时间不能小于第二次提醒时间");
            }
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<TaskRemindBO> list(TaskRemindDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<TaskRemind> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, TaskRemindBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        TaskRemind entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }


    @Override
    public Long count(TaskRemindDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public TaskRemindBO findByID(String id) throws SerException {
        TaskRemind entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, TaskRemindBO.class);
    }

}