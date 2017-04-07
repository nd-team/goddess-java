package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.entity.ExternalContacts;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 外部通讯录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class ExternalContactsSerImpl extends ServiceImpl<ExternalContacts, ExternalContactsDTO> implements ExternalContactsSer {

    @Autowired
    private UserAPI userAPI;


    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        ExternalContacts entity = BeanTransform.copyProperties(to, ExternalContacts.class, true);
        UserBO user = userAPI.currentUser();
        entity.setWriter(user.getUsername());
        entity.setWriteNumber(user.getEmployeeNumber());
        entity.setWriteTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO update(ExternalContactsTO to) throws SerException {
        ExternalContacts entity = BeanTransform.copyProperties(to, ExternalContacts.class, true), contacts = super.findById(to.getId());
        entity.setCreateTime(contacts.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        UserBO user = userAPI.currentUser();
        entity.setWriter(user.getUsername());
        entity.setWriteNumber(user.getEmployeeNumber());
        entity.setWriteTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExternalContactsBO delete(ExternalContactsTO to) throws SerException {
        ExternalContacts entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ExternalContactsBO.class);
    }

    @Override
    public List<ExternalContactsBO> findByArea(String area) throws SerException {
        ExternalContactsDTO dto = new ExternalContactsDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<ExternalContacts> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ExternalContactsBO.class);
    }

    @Override
    public List<ExternalContactsBO> maps(ExternalContactsDTO dto) throws SerException {
        dto.getSorts().add("writeTime=desc");
        List<ExternalContacts> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ExternalContactsBO.class);
    }
}