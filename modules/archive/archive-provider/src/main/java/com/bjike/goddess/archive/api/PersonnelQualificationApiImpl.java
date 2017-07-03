package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.PersonnelQualificationBO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.service.PersonnelQualificationSer;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员资质业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("personnelQualificationApiImpl")
public class PersonnelQualificationApiImpl implements PersonnelQualificationAPI {

    @Autowired
    private PersonnelQualificationSer personnelQualificationSer;

    @Override
    public PersonnelQualificationBO save(PersonnelQualificationTO to) throws SerException {
        return personnelQualificationSer.save(to);
    }

    @Override
    public PersonnelQualificationBO update(PersonnelQualificationTO to) throws SerException {
        return personnelQualificationSer.update(to);
    }

    @Override
    public PersonnelQualificationBO delete(String id) throws SerException {
        return personnelQualificationSer.delete(id);
    }

    @Override
    public List<PersonnelQualificationBO> maps(PersonnelQualificationDTO dto) throws SerException {
        return personnelQualificationSer.maps(dto);
    }

    @Override
    public PersonnelQualificationBO getById(String id) throws SerException {
        return personnelQualificationSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return personnelQualificationSer.getTotal();
    }
}