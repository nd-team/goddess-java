package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;

/**
 * 人员信息资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonnelInformationSer extends Ser<PersonnelInformation, PersonnelInformationDTO> {

    default PersonnelInformationBO save(PersonnelInformationTO to) throws SerException {
        return null;
    }

    default PersonnelInformationBO update(PersonnelInformationTO to) throws SerException {
        return null;
    }

    default PersonnelInformationBO delete(String id) throws SerException {
        return null;
    }


}