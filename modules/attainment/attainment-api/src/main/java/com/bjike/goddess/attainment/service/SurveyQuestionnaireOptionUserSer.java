package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionUserBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOptionUser;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 问卷填写信息表业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireOptionUserSer extends Ser<SurveyQuestionnaireOptionUser, SurveyQuestionnaireOptionUserDTO> {

    default SurveyQuestionnaireOptionUserBO save(SurveyQuestionnaireOptionUserTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireOptionUserBO update(SurveyQuestionnaireOptionUserTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireOptionUserBO delete(SurveyQuestionnaireOptionUserTO to) throws SerException {
        return null;
    }

    default List<SurveyQuestionnaireOptionUserBO> findByOption(String option_id) throws SerException {
        return null;
    }
}