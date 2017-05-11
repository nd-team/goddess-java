package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.entity.InvolvedProcessingTask;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 参与处理人员的任务分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class InvolvedProcessingTaskSerImpl extends ServiceImpl<InvolvedProcessingTask, InvolvedProcessingTaskDTO> implements InvolvedProcessingTaskSer {
    @Override
    public Long countInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        involvedProcessingTaskDTO.getSorts().add("createTime=desc");
        Long counts = super.count(involvedProcessingTaskDTO);
        return counts;
    }
    @Override
    public InvolvedProcessingTaskBO getOne(String id) throws SerException {
        InvolvedProcessingTask involvedProcessingTask = super.findById(id);
        return BeanTransform.copyProperties(involvedProcessingTask,InvolvedProcessingTaskBO.class,true);
    }

    @Override
    public List<InvolvedProcessingTaskBO> findListInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        involvedProcessingTaskDTO.getSorts().add("createTime=desc");
        List<InvolvedProcessingTask> involvedProcessingTasks = super.findByCis(involvedProcessingTaskDTO, true);
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = BeanTransform.copyProperties(involvedProcessingTasks, InvolvedProcessingTaskBO.class);
        return involvedProcessingTaskBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvolvedProcessingTaskBO insertInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        InvolvedProcessingTask involvedProcessingTask = BeanTransform.copyProperties(involvedProcessingTaskTO, InvolvedProcessingTask.class, true);
        involvedProcessingTask.setCreateTime(LocalDateTime.now());
        super.save(involvedProcessingTask);
        return BeanTransform.copyProperties(involvedProcessingTask, InvolvedProcessingTaskBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvolvedProcessingTaskBO editInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        InvolvedProcessingTask involvedProcessingTask = super.findById(involvedProcessingTaskTO.getId());
        BeanTransform.copyProperties(involvedProcessingTaskTO, involvedProcessingTask, true);
        involvedProcessingTask.setModifyTime(LocalDateTime.now());
        super.update(involvedProcessingTask);
        return BeanTransform.copyProperties(involvedProcessingTaskTO, InvolvedProcessingTaskBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeInvolvedProcessingTask(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String internalProjectName, String handler) throws SerException {
        //TODO: xiazhili 2017-03-25 未做导出
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<InvolvedProcessingTaskBO> searchInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if(StringUtils.isNotBlank(involvedProcessingTaskDTO.getInternalProjectName())){
            involvedProcessingTaskDTO.getConditions().add(Restrict.eq("internalProjectName",involvedProcessingTaskDTO.getInternalProjectName()));
        }
        /**
         * 处理人员
         */
        if(StringUtils.isNotBlank(involvedProcessingTaskDTO.getHandler())){
            involvedProcessingTaskDTO.getConditions().add(Restrict.eq("handler",involvedProcessingTaskDTO.getHandler()));
        }
        List<InvolvedProcessingTask> involvedProcessingTasks = super.findByCis(involvedProcessingTaskDTO,true);
        List<InvolvedProcessingTaskBO> involvedProcessingTaskBOS = BeanTransform.copyProperties(involvedProcessingTasks,InvolvedProcessingTaskBO.class);
        return involvedProcessingTaskBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-25 未做上传
        return;

    }

}