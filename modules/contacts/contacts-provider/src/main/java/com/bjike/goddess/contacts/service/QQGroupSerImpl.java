package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.QQGroupBO;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.entity.QQGroup;
import com.bjike.goddess.contacts.to.QQGroupTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * QQ群管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class QQGroupSerImpl extends ServiceImpl<QQGroup, QQGroupDTO> implements QQGroupSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO save(QQGroupTO to) throws SerException {
        QQGroup entity = BeanTransform.copyProperties(to, QQGroup.class);
        entity.isStatus(Boolean.TRUE);
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO update(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.isStatus(Boolean.TRUE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO delete(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QQGroupBO close(QQGroupTO to) throws SerException {
        QQGroup entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.isStatus(Boolean.FALSE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Override
    public List<QQGroupBO> maps(QQGroupDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        List<QQGroup> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QQGroupBO.class);
    }

    @Override
    public QQGroupBO getById(String id) throws SerException {
        QQGroup entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, QQGroupBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        QQGroupDTO dto = new QQGroupDTO();
        return super.count(dto);
    }
}