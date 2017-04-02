package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.CommerceContactsBO;
import com.bjike.goddess.contacts.dto.CommerceContactsDTO;
import com.bjike.goddess.contacts.entity.CommerceContacts;
import com.bjike.goddess.contacts.to.CommerceContactsTO;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 商务通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommerceContactsSerImpl extends ServiceImpl<CommerceContacts, CommerceContactsDTO> implements CommerceContactsSer {

    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceContactsBO save(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = BeanTransform.copyProperties(to, CommerceContacts.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceContactsBO update(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = BeanTransform.copyProperties(to, CommerceContacts.class), contacts = super.findById(to.getId());
        entity.setCreateTime(contacts.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(CommerceContactsTO to) throws SerException {
        super.remove(to.getId());
    }

}