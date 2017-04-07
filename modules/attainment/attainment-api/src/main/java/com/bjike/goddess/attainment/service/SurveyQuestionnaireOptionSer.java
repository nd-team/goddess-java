package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研表 -> 问题 -> 选项业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表 -> 问题 -> 选项业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireOptionSer extends Ser<SurveyQuestionnaireOption, SurveyQuestionnaireOptionDTO> {

    default SurveyQuestionnaireOptionBO save(SurveyQuestionnaireOptionTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireOptionBO update(SurveyQuestionnaireOptionTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireOptionBO delete(String id) throws SerException {
        return null;
    }

    default List<SurveyQuestionnaireOptionBO> findByQuestion(String questionnaire_id) throws SerException {
        return null;
    }
}