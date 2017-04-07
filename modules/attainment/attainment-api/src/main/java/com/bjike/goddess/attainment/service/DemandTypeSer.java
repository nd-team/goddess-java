package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.DemandTypeBO;
import com.bjike.goddess.attainment.dto.DemandTypeDTO;
import com.bjike.goddess.attainment.entity.DemandType;
import com.bjike.goddess.attainment.to.DemandTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研需求类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandTypeSer extends Ser<DemandType, DemandTypeDTO> {

    default DemandTypeBO save(DemandTypeTO to) throws SerException {
        return null;
    }

    default DemandTypeBO update(DemandTypeTO to) throws SerException {
        return null;
    }

    default DemandTypeBO delete(String id) throws SerException {
        return null;
    }

    default DemandTypeBO congeal(String id) throws SerException {
        return null;
    }

    default DemandTypeBO thaw(String id) throws SerException {
        return null;
    }

    default List<DemandTypeBO> findThaw() throws SerException {
        return null;
    }

}