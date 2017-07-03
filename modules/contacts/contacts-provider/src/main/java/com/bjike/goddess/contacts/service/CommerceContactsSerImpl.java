package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
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
import java.util.List;

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
        CommerceContactsDTO dto = new CommerceContactsDTO();
        dto.getConditions().add(Restrict.eq("customerNum", to.getCustomerNum()));
        if (super.count(dto) != 0)
            throw new SerException(to.getCustomerNum() + ":该编号以已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceContactsBO update(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不村在");
        if (!to.getCustomerNum().equals(entity.getCustomerNum())) {
            CommerceContactsDTO dto = new CommerceContactsDTO();
            dto.getConditions().add(Restrict.eq("customerNum", to.getCustomerNum()));
            if (super.count(dto) != 0)
                throw new SerException(to.getCustomerNum() + ":该编号以已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(CommerceContactsTO to) throws SerException {
        CommerceContacts entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不村在");
        super.remove(entity);
    }

    @Override
    public List<CommerceContactsBO> maps(CommerceContactsDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), CommerceContactsBO.class);
    }

    @Override
    public CommerceContactsBO getById(String id) throws SerException {
        CommerceContacts entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不村在");
        return BeanTransform.copyProperties(entity, CommerceContactsBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CommerceContactsDTO dto = new CommerceContactsDTO();
        return super.count(dto);
    }
}