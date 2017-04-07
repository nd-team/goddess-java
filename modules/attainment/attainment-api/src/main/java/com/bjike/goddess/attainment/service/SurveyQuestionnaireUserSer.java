package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireUserBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireUser;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 问卷调查历史记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireUserSer extends Ser<SurveyQuestionnaireUser, SurveyQuestionnaireUserDTO> {

    default SurveyQuestionnaireUserBO save(SurveyQuestionnaireUserTO to) throws SerException {
        return null;
    }

    default SurveyQuestionnaireUserBO delete(String id) throws SerException {
        return null;
    }

    default List<SurveyQuestionnaireUserBO> findByActualize(String actualize_id) throws SerException {
        return null;
    }
}