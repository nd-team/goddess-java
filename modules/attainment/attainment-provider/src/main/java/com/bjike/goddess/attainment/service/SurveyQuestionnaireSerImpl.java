package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研表问题业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyQuestionnaireSerImpl extends ServiceImpl<SurveyQuestionnaire, SurveyQuestionnaireDTO> implements SurveyQuestionnaireSer {

    @Autowired
    private SurveyActualizeSer surveyActualizeSer;

    @Autowired
    private SurveyQuestionnaireOptionSer surveyQuestionnaireOptionSer;

    private SurveyQuestionnaireBO transformBO(SurveyQuestionnaire entity) {
        SurveyQuestionnaireBO bo = BeanTransform.copyProperties(entity, SurveyQuestionnaireBO.class);
        bo.setActualize_id(entity.getActualize().getId());
        return bo;
    }

    private List<SurveyQuestionnaireBO> transformBOList(List<SurveyQuestionnaire> list) {
        List<SurveyQuestionnaireBO> bos = new ArrayList<>(list.size());
        for (SurveyQuestionnaire entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireBO save(SurveyQuestionnaireTO to) throws SerException {
        SurveyQuestionnaire entity = BeanTransform.copyProperties(to, SurveyQuestionnaire.class);
        entity.setActualize(surveyActualizeSer.findById(to.getId()));
        if (null == entity.getActualize())
            throw new SerException("调研实施对象不存在,无法保存");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireBO update(SurveyQuestionnaireTO to) throws SerException {
        SurveyQuestionnaire entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setActualize(surveyActualizeSer.findById(to.getId()));
        if (null == entity.getActualize())
            throw new SerException("调研实施对象不存在,无法保存");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireBO delete(String id) throws SerException {
        SurveyQuestionnaire entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if(surveyQuestionnaireOptionSer.findByQuestion(entity.getId()).size()!=0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyQuestionnaireBO> findByActualize(String actualize_id) throws SerException {
        SurveyQuestionnaireDTO dto = new SurveyQuestionnaireDTO();
        dto.getConditions().add(Restrict.eq("actualize.id", actualize_id));
        List<SurveyQuestionnaire> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}