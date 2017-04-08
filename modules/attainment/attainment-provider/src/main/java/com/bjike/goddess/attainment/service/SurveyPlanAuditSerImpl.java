package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanAuditBO;
import com.bjike.goddess.attainment.dto.SurveyPlanAuditDTO;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.attainment.entity.SurveyPlanAudit;
import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研计划审核记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyPlanAuditSerImpl extends ServiceImpl<SurveyPlanAudit, SurveyPlanAuditDTO> implements SurveyPlanAuditSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private SurveyPlanSer surveyPlanSer;

    private SurveyPlanAuditBO transformBO(SurveyPlanAudit entity) throws SerException {
        SurveyPlanAuditBO bo = BeanTransform.copyProperties(entity, SurveyPlanAudit.class);
        bo.setPlan_id(entity.getPlan().getId());
        return bo;
    }

    private List<SurveyPlanAuditBO> transformBOList(List<SurveyPlanAudit> list) throws SerException {
        List<SurveyPlanAuditBO> bos = new ArrayList<>(list.size());
        for (SurveyPlanAudit entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public SurveyPlanAuditBO update(SurveyPlanAuditTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        UserDetailBO userDetail = userDetailAPI.findByUserId(user.getId());
        SurveyPlanAuditBO auditBO = this.findByUserPlan(to.getPlan_id(), user.getUsername());
        SurveyPlanAudit entity;
        SurveyPlan plan = surveyPlanSer.findById(to.getPlan_id());
        if (null == auditBO) {
            entity = BeanTransform.copyProperties(to, SurveyPlanAudit.class, true);
            entity.setAuditor(user.getUsername());
            entity.setAuditTime(LocalDateTime.now());
            entity.setPosition(userDetail.getPositionName());
            entity.setDepartment(userDetail.getDepartmentName());
            entity.setPlan(plan);
            super.save(entity);
        } else {
            entity = BeanTransform.copyProperties(auditBO, SurveyPlanAudit.class, true);
            entity.setPlan(plan);
            entity.setAuditTime(LocalDateTime.now());
            super.update(entity);
        }
        if (null == plan)
            throw new SerException("研究计划不能为空");
        if (entity.isPass())
            plan.setAudit(AuditType.ALLOWED);
        else
            plan.setAudit(AuditType.NONE);
        surveyPlanSer.update(plan);
        return this.transformBO(entity);
    }

    @Override
    public SurveyPlanAuditBO findByUserPlan(String plan_id, String auditor) throws SerException {
        SurveyPlanAuditDTO dto = new SurveyPlanAuditDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        dto.getConditions().add(Restrict.eq("auditor", auditor));
        dto.getSorts().add("auditTime=desc");
        List<SurveyPlanAudit> list = super.findByCis(dto);
        if (list.size() > 0)
            return this.transformBO(list.get(0));
        return null;
    }

    @Override
    public SurveyPlanAuditBO delete(String id) throws SerException {
        SurveyPlanAudit entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyPlanAuditBO> findByPlan(String plan_id) throws SerException {
        SurveyPlanAuditDTO dto = new SurveyPlanAuditDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        dto.getSorts().add("auditTime=desc");
        List<SurveyPlanAudit> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}