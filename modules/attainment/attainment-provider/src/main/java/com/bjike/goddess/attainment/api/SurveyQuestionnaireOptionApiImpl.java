package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionBO;
import com.bjike.goddess.attainment.service.SurveyQuestionnaireOptionSer;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研表问题选项业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyQuestionnaireOptionApiImpl")
public class SurveyQuestionnaireOptionApiImpl implements SurveyQuestionnaireOptionAPI {

    @Autowired
    private SurveyQuestionnaireOptionSer surveyQuestionnaireOptionSer;

    @Override
    public SurveyQuestionnaireOptionBO save(SurveyQuestionnaireOptionTO to) throws SerException {
        return surveyQuestionnaireOptionSer.save(to);
    }

    @Override
    public SurveyQuestionnaireOptionBO update(SurveyQuestionnaireOptionTO to) throws SerException {
        return surveyQuestionnaireOptionSer.update(to);
    }

    @Override
    public SurveyQuestionnaireOptionBO delete(String id) throws SerException {
        return surveyQuestionnaireOptionSer.delete(id);
    }

    @Override
    public List<SurveyQuestionnaireOptionBO> findByQuestion(String questionnaire_id) throws SerException {
        return surveyQuestionnaireOptionSer.findByQuestion(questionnaire_id);
    }
}