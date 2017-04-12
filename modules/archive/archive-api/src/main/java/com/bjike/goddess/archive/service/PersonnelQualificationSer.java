package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.PersonnelQualificationBO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.entity.PersonnelQualification;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 人员资质业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonnelQualificationSer extends Ser<PersonnelQualification, PersonnelQualificationDTO> {

    default PersonnelQualificationBO save(PersonnelQualificationTO to) throws SerException {
        return null;
    }

    default PersonnelQualificationBO update(PersonnelQualificationTO to) throws SerException {
        return null;
    }

    default PersonnelQualificationBO delete(String id) throws SerException {
        return null;
    }

    default List<PersonnelQualificationBO> maps(PersonnelQualificationDTO dto) throws SerException {
        return null;
    }

}