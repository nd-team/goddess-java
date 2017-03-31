package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.QualificationsHandlePlanDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandlePlan;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;

import java.util.List;

/**
 * 资质办理计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsHandlePlanSer extends Ser<QualificationsHandlePlan, QualificationsHandlePlanDTO> {

    default QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        return null;
    }

    default QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        return null;
    }

    default QualificationsHandlePlanBO delete(String id) throws SerException {
        return null;
    }

    default List<QualificationsHandlePlanBO> findByHandle(String handle_id) throws SerException {
        return null;
    }

}