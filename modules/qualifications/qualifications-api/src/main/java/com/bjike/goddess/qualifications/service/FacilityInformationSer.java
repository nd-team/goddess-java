package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.FacilityInformationBO;
import com.bjike.goddess.qualifications.dto.FacilityInformationDTO;
import com.bjike.goddess.qualifications.entity.FacilityInformation;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;

/**
 * 设备信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FacilityInformationSer extends Ser<FacilityInformation, FacilityInformationDTO> {

    default FacilityInformationBO save(FacilityInformationTO to) throws SerException {
        return null;
    }

    default FacilityInformationBO update(FacilityInformationTO to) throws SerException {
        return null;
    }

    default FacilityInformationBO delete(String id) throws SerException {
        return null;
    }

}