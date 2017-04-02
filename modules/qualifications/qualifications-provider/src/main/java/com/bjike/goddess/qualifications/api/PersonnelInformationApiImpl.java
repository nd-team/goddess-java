package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.service.PersonnelInformationSer;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员信息资料业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("personnelInformationApiImpl")
public class PersonnelInformationApiImpl implements PersonnelInformationAPI {

    @Autowired
    private PersonnelInformationSer personnelInformationSer;

    @Override
    public PersonnelInformationBO save(PersonnelInformationTO to) throws SerException {
        return personnelInformationSer.save(to);
    }

    @Override
    public PersonnelInformationBO update(PersonnelInformationTO to) throws SerException {
        return personnelInformationSer.update(to);
    }

    @Override
    public PersonnelInformationBO delete(String id) throws SerException {
        return personnelInformationSer.delete(id);
    }

    @Override
    public List<PersonnelInformationBO> all() throws SerException {
        return personnelInformationSer.all();
    }
}