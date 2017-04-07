package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.entity.SurveyActualize;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 调研实施记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyActualizeSer extends Ser<SurveyActualize, SurveyActualizeDTO> {

    default SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        return null;
    }

    default SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        return null;
    }

    default SurveyActualizeBO delete(String id) throws SerException {
        return null;
    }

    default SurveyActualizeBO over(String id) throws SerException {
        return null;
    }

}