package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人员信息资料业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class PersonnelInformationSerImpl extends ServiceImpl<PersonnelInformation, PersonnelInformationDTO> implements PersonnelInformationSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelInformationBO save(PersonnelInformationTO to) throws SerException {
        PersonnelInformation entity = BeanTransform.copyProperties(to, PersonnelInformation.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, PersonnelInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelInformationBO update(PersonnelInformationTO to) throws SerException {
        PersonnelInformation entity = BeanTransform.copyProperties(to, PersonnelInformation.class), personnel = super.findById(to.getId());
        entity.setCreateTime(personnel.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, PersonnelInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelInformationBO delete(String id) throws SerException {
        PersonnelInformation entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, PersonnelInformationBO.class);
    }

    @Override
    public List<PersonnelInformationBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), PersonnelInformationBO.class);
    }
}