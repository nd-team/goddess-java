package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

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
        PersonnelInformation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, PersonnelInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelInformationBO delete(String id) throws SerException {
        PersonnelInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        List<QualificationsHandle> list = qualificationsHandleSer.findAll();
        for (QualificationsHandle handle : list)
            if (handle.getPersonnelSet().stream().filter(m -> m.getId().equals(id)).count() != 0)
                throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, PersonnelInformationBO.class);
    }

    @Override
    public List<PersonnelInformationBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), PersonnelInformationBO.class);
    }

    @Override
    public PersonnelInformationBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), PersonnelInformationBO.class);
    }
}