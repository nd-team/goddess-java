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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private SurveyAnalyseBO transformBO(SurveyAnalyse entity) throws SerException {
        SurveyAnalyseBO bo = BeanTransform.copyProperties(surveyPlanSer.findBOById(entity.getPlan().getId()), SurveyAnalyseBO.class);
        bo.setPlan_id(entity.getPlan().getId());
        BeanTransform.copyProperties(entity, bo, true);
        return bo;
    }

    private List<SurveyAnalyseBO> transformBOList(List<SurveyAnalyse> list) throws SerException {
        List<SurveyAnalyseBO> bos = new ArrayList<>(list.size());
        for (SurveyAnalyse entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyAnalyseBO save(SurveyAnalyseTO to) throws SerException {
        SurveyAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyse.class, true);
        entity.setPlan(surveyPlanSer.findById(to.getPlan_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyAnalyseBO update(SurveyAnalyseTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SurveyAnalyse entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
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
    public SurveyAnalyseBO delete(String id) throws SerException {
        SurveyAnalyse entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyAnalyseBO> findByPlan(String plan_id) throws SerException {
        SurveyAnalyseDTO dto = new SurveyAnalyseDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        List<SurveyAnalyse> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SkillAnalyseBO.class);
    }


}