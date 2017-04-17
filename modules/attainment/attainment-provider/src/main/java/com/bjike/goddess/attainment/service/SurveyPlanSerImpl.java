package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanBO;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.attainment.to.SurveyPlanTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyPlanSerImpl extends ServiceImpl<SurveyPlan, SurveyPlanDTO> implements SurveyPlanSer {

    @Autowired
    private SurveyDemandSer demandSer;

    private SurveyPlanBO transformBO(SurveyPlan entity) throws SerException {
        SurveyPlanBO bo = BeanTransform.copyProperties(entity, SurveyPlanBO.class);
        SurveyDemand demand = entity.getDemand();
        bo.setDemand_id(demand.getId());
        bo.setDemandName(demand.getDemand().getType());
        bo.setPurpose(demand.getPurpose());
        bo.setScope(demand.getScope());
        bo.setTypeName(demand.getType().getType());
        bo.setUsername(demand.getUsername());
        bo.isRegular(demand.getType().isRegular());
        bo.setTheme(demand.getTheme());
        bo.setPurpose(demand.getPurpose());
        bo.setScope(demand.getScope());
        bo.setScopeName(demand.getScopeName());
        return bo;
    }

    private List<SurveyPlanBO> transformBOList(List<SurveyPlan> list) throws SerException {
        List<SurveyPlanBO> bos = new ArrayList<>(list.size());
        for (SurveyPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO save(SurveyPlanTO to) throws SerException {
        SurveyPlan entity = BeanTransform.copyProperties(to, SurveyPlan.class, true);
        entity.setDemand(demandSer.findById(to.getDemand_id()));
        entity.setAudit(AuditType.NONE);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO update(SurveyPlanTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SurveyPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setDemand(demandSer.findById(to.getDemand_id()));
                super.update(entity);
                return this.transformBO(entity);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO delete(String id) throws SerException {
        SurveyPlan entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyPlanBO> findByDemand(String demand_id) throws SerException {
        SurveyPlanDTO dto = new SurveyPlanDTO();
        dto.getConditions().add(Restrict.eq("demand.id", demand_id));
        List<SurveyPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public SurveyPlanBO findBOById(String id) throws SerException {
        return this.transformBO(super.findById(id));
    }
}