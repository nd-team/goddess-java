package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.dto.AttainmentTypeDTO;
import com.bjike.goddess.attainment.entity.AttainmentType;
import com.bjike.goddess.attainment.to.AttainmentTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:49 ]
 * @Description: [ 调研类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttainmentTypeSer extends Ser<AttainmentType, AttainmentTypeDTO> {


    default AttainmentTypeBO save(AttainmentTypeTO to) throws SerException {
        return null;
    }

    default AttainmentTypeBO update(AttainmentTypeTO to) throws SerException {
        return null;
    }

    default AttainmentTypeBO delete(String id) throws SerException {
        return null;
    }

    default AttainmentTypeBO congeal(String id) throws SerException {
        return null;
    }

    default AttainmentTypeBO thaw(String id) throws SerException {
        return null;
    }

    default List<AttainmentTypeBO> findThaw() throws SerException {
        return null;
    }

    default List<AttainmentTypeBO> findRegular(Boolean regular) throws SerException {
        return null;
    }


}