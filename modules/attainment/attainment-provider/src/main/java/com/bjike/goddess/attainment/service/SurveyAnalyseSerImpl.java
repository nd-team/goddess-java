package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.bo.SurveyAnalyseBO;
import com.bjike.goddess.attainment.dto.SurveyAnalyseDTO;
import com.bjike.goddess.attainment.entity.SkillAnalyse;
import com.bjike.goddess.attainment.entity.SurveyAnalyse;
import com.bjike.goddess.attainment.to.SurveyAnalyseTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研分析业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyAnalyseSerImpl extends ServiceImpl<SurveyAnalyse, SurveyAnalyseDTO> implements SurveyAnalyseSer {

    @Autowired
    private SurveyPlanSer surveyPlanSer;

    @Override
    public SurveyAnalyseBO save(SurveyAnalyseTO to) throws SerException {
        SurveyAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyse.class);
        entity.setPlan(surveyPlanSer.findById(to.getPlan_id()));
        super.save(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public SurveyAnalyseBO update(SurveyAnalyseTO to) throws SerException {
        SurveyAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyse.class), analyse = super.findById(to.getId());
        if (null == analyse)
            throw new SerException("程序错误,请刷新重试");
        entity.setPlan(surveyPlanSer.findById(to.getPlan_id()));
        entity.setCreateTime(analyse.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public SurveyAnalyseBO delete(String id) throws SerException {
        SurveyAnalyse entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public List<SurveyAnalyseBO> findByPlan(String plan_id) throws SerException {
        SurveyAnalyseDTO dto = new SurveyAnalyseDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        List<SurveyAnalyse> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SkillAnalyseBO.class);
    }


}