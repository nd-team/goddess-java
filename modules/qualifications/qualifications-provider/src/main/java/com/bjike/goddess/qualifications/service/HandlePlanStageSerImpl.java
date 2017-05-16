package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.HandlePlanStageBO;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.HandlePlanStageDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资质办理计划阶段划分业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class HandlePlanStageSerImpl extends ServiceImpl<HandlePlanStage, HandlePlanStageDTO> implements HandlePlanStageSer {

    @Autowired
    private QualificationsHandlePlanSer handlePlanSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO save(HandlePlanStageTO to) throws SerException {
        HandlePlanStage entity = BeanTransform.copyProperties(to, HandlePlanStage.class, true);
        entity.setPlan(handlePlanSer.findById(to.getPlanId()));
        super.save(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO update(HandlePlanStageTO to) throws SerException {
        HandlePlanStage entity = BeanTransform.copyProperties(to, HandlePlanStage.class, true), stage = super.findById(to.getId());
        entity.setCreateTime(stage.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        entity.setPlan(handlePlanSer.findById(to.getPlanId()));
        super.update(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HandlePlanStageBO delete(String id) throws SerException {
        HandlePlanStage entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByPlanIds(String[] planIds) throws SerException {
        if (planIds == null || planIds.length == 0)
            return null;
        HandlePlanStageDTO dto = new HandlePlanStageDTO();
        dto.getConditions().add(Restrict.in("plan.id", planIds));
        dto.getSorts().add("plan.id");
        List<HandlePlanStage> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByPlan(String planId) throws SerException {
        HandlePlanStageDTO dto = new HandlePlanStageDTO();
        dto.getConditions().add(Restrict.eq("plan.id", planId));
        List<HandlePlanStage> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HandlePlanStageBO.class);
    }

    @Override
    public List<HandlePlanStageBO> findByHandle(String handleId) throws SerException {
        List<String> planIds = handlePlanSer.findByHandle(handleId).stream().map(QualificationsHandlePlanBO::getId).collect(Collectors.toList());
        if (planIds.size() != 0)
            return this.findByPlanIds(planIds.toArray(new String[0]));
        else
            return null;
    }

    @Override
    public HandlePlanStageBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), HandlePlanStageBO.class);
    }
}