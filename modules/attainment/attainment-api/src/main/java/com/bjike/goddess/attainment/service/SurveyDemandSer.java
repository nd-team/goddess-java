package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研需求业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyDemandSer extends Ser<SurveyDemand, SurveyDemandDTO> {

    default SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        return null;
    }

    default SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        return null;
    }

    default SurveyDemandBO delete(String id) throws SerException {
        return null;
    }

    default SurveyDemandBO close(CloseDemandTO to) throws SerException {
        return null;
    }

    default List<SurveyDemandBO> findByStatus(SurveyStatus status) throws SerException {
        return null;
    }


}