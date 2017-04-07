package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研表 -> 问题业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表 -> 问题业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireSer extends Ser<SurveyQuestionnaire, SurveyQuestionnaireDTO> {

    default SurveyQuestionnaireBO save(SurveyQuestionnaireTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireBO update(SurveyQuestionnaireTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireBO delete(String id) throws SerException {
        return null;
    }

    default List<SurveyQuestionnaireBO> findByActualize(String actualize_id) throws SerException {
        return null;
    }

}