package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.OtherContactsBO;
import com.bjike.goddess.contacts.dto.OtherContactsDTO;
import com.bjike.goddess.contacts.entity.OtherContacts;
import com.bjike.goddess.contacts.to.OtherContactsTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 其他通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class OtherContactsSerImpl extends ServiceImpl<OtherContacts, OtherContactsDTO> implements OtherContactsSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherContactsBO save(OtherContactsTO to) throws SerException {
        OtherContacts entity = BeanTransform.copyProperties(to, OtherContacts.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, OtherContactsBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherContactsBO update(OtherContactsTO to) throws SerException {
        OtherContacts entity = BeanTransform.copyProperties(to, OtherContacts.class, true), contacts = super.findById(to.getId());
        entity.setCreateTime(contacts.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, OtherContactsBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherContactsBO delete(OtherContactsTO to) throws SerException {
        OtherContacts entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, OtherContactsBO.class, true);
    }

    @Override
    public List<OtherContactsBO> maps(OtherContactsDTO dto) throws SerException {
        List<OtherContacts> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, OtherContactsBO.class, true);
    }
}