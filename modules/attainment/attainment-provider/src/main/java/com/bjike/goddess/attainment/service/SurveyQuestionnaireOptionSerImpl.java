package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研表问题选项业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyQuestionnaireOptionSerImpl extends ServiceImpl<SurveyQuestionnaireOption, SurveyQuestionnaireOptionDTO> implements SurveyQuestionnaireOptionSer {

    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;

    private SurveyQuestionnaireOptionBO transformBO(SurveyQuestionnaireOption entity) throws SerException {
        SurveyQuestionnaireOptionBO bo = BeanTransform.copyProperties(entity, SurveyQuestionnaireOptionBO.class);
        bo.setQuestionnaire_id(entity.getQuestionnaire().getId());
        bo.setQuestionnaireName(entity.getQuestionnaire().getQuestionnaire());
        return bo;
    }

    private List<SurveyQuestionnaireOptionBO> transformBOList(List<SurveyQuestionnaireOption> list) throws SerException {
        List<SurveyQuestionnaireOptionBO> bos = new ArrayList<>(list.size());
        for (SurveyQuestionnaireOption entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public SurveyQuestionnaireOptionBO save(SurveyQuestionnaireOptionTO to) throws SerException {
        SurveyQuestionnaireOption entity = BeanTransform.copyProperties(to, SurveyQuestionnaireOption.class);
        entity.setBallot(0);
        entity.setQuestionnaire(surveyQuestionnaireSer.findById(to.getId()));
        return this.transformBO(entity);
    }

    @Override
    public SurveyQuestionnaireOptionBO update(SurveyQuestionnaireOptionTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SurveyQuestionnaireOption entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setQuestionnaire(surveyQuestionnaireSer.findById(to.getQuestionnaire_id()));
                super.update(entity);
                return this.transformBO(entity);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public SurveyQuestionnaireOptionBO delete(String id) throws SerException {
        SurveyQuestionnaireOption entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyQuestionnaireOptionBO> findByQuestion(String questionnaire_id) throws SerException {
        SurveyQuestionnaireOptionDTO dto = new SurveyQuestionnaireOptionDTO();
        dto.getConditions().add(Restrict.eq("questionnaire.id", questionnaire_id));
        List<SurveyQuestionnaireOption> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}