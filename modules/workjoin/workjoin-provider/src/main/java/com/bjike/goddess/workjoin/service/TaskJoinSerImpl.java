package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.entity.TaskJoin;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import net.sf.ehcache.store.compound.LegacyCopyStrategyAdapter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 任务交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class TaskJoinSerImpl extends ServiceImpl<TaskJoin, TaskJoinDTO> implements TaskJoinSer {

    @Override
    public Long countTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        Long count = super.count(taskJoinDTO);
        return count;
    }

    @Override
    public TaskJoinBO getOne(String id) throws SerException {
        TaskJoin taskJoin = super.findById(id);
        return BeanTransform.copyProperties(taskJoin,TaskJoinBO.class);
    }

    @Override
    public List<TaskJoinBO> findListTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        List<TaskJoin> taskJoins = super.findByPage(taskJoinDTO);
        List<TaskJoinBO> taskJoinBOS = BeanTransform.copyProperties(taskJoins,TaskJoinBO.class);
        return taskJoinBOS;
    }

    @Override
    public TaskJoinBO insertTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        TaskJoin taskJoin = BeanTransform.copyProperties(taskJoinTO,TaskJoin.class,true);
        taskJoin.setCreateTime(LocalDateTime.now());
        super.save(taskJoin);
        return BeanTransform.copyProperties(taskJoin,TaskJoinBO.class);
    }

    @Override
    public TaskJoinBO editTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        TaskJoin taskJoin = super.findById(taskJoinTO.getId());
        BeanTransform.copyProperties(taskJoinTO,taskJoin,true);
        taskJoin.setModifyTime(LocalDateTime.now());
        super.update(taskJoin);
        return BeanTransform.copyProperties(taskJoinTO,TaskJoinBO.class,true);
    }

    @Override
    public void removeTaskJoin(String id) throws SerException {
        super.remove(id);
    }
}