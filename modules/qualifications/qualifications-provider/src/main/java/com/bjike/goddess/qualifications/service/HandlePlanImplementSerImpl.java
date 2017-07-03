package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.HandlePlanImplementBO;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
import com.bjike.goddess.qualifications.dto.HandlePlanImplementDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanImplement;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;
import com.bjike.goddess.qualifications.to.HandlePlanImplementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资质办理计划阶段实施工作记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class HandlePlanImplementSerImpl extends ServiceImpl<HandlePlanImplement, HandlePlanImplementDTO> implements HandlePlanImplementSer {

    @Autowired
    private HandlePlanStageSer planStageSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanImplementBO save(HandlePlanImplementTO to) throws SerException {
        HandlePlanImplement entity = BeanTransform.copyProperties(to, HandlePlanImplement.class, true);
        HandlePlanStage stage = planStageSer.findById(to.getStageId());
        if (null == stage)
            throw new SerException("指定阶段不存在");
        if (entity.getFinishTime().isAfter(stage.getFinishTime()))
            throw new SerException("完成时间不能超过计划完成时间");
        entity.setStage(stage);
        super.save(entity);
        return BeanTransform.copyProperties(entity, HandlePlanImplementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanImplementBO update(HandlePlanImplementTO to) throws SerException {
        HandlePlanImplement entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        HandlePlanStage stage = planStageSer.findById(to.getStageId());
        if (null == stage)
            throw new SerException("指定阶段不存在");
        if (entity.getFinishTime().isAfter(stage.getFinishTime()))
            throw new SerException("完成时间不能超过计划完成时间");
        entity.setStage(stage);
        super.update(entity);
        return BeanTransform.copyProperties(entity, HandlePlanImplementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanImplementBO delete(String id) throws SerException {
        HandlePlanImplement entity = super.findById(id);
        if(null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, HandlePlanImplementBO.class);
    }

    @Override
    public List<HandlePlanImplementBO> findByStageIds(String[] stageIds) throws SerException {
        if (null == stageIds || stageIds.length == 0)
            return null;
        HandlePlanImplementDTO dto = new HandlePlanImplementDTO();
        dto.getConditions().add(Restrict.in("stage.id", stageIds));
        dto.getSorts().add("stage.id");
        List<HandlePlanImplement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanImplementBO.class);
    }

    @Override
    public List<HandlePlanImplementBO> findByStage(String stageId) throws SerException {
        HandlePlanImplementDTO dto = new HandlePlanImplementDTO();
        dto.getConditions().add(Restrict.eq("stage.id", stageId));
        List<HandlePlanImplement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanImplementBO.class);
    }

    @Override
    public List<HandlePlanImplementBO> findByHandle(String handleId) throws SerException {
        List<String> stageIds = planStageSer.findByHandle(handleId).stream().map(HandlePlanStageBO::getId).collect(Collectors.toList());
        return this.findByStageIds(stageIds.toArray(new String[0]));
    }

    @Override
    public HandlePlanImplementBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), HandlePlanImplementBO.class);
    }
}