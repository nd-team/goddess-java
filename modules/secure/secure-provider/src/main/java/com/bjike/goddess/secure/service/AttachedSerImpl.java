package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.to.AttachedTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 挂靠业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedSerImpl extends ServiceImpl<Attached, AttachedDTO> implements AttachedSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO save(AttachedTO to) throws SerException {
        Attached attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached = super.save(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO exameAndComplete(AttachedTO to) throws SerException {
        Attached attached = super.findById(to.getId());
        attached.setMoney(to.getMoney());
        attached.setPaid(to.getPaid());
        attached.setRecord(to.getRecord());
        attached.setDecription(to.getDecription());
        attached.setAdvice(to.getAdvice());
        super.update(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<AttachedBO> find(AttachedDTO dto) throws SerException {
        List<Attached> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    public AttachedBO findByID(String id) throws SerException {
        Attached attached = super.findById(id);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public List<AttachedBO> findALL() throws SerException {
        List<Attached> list = super.findAll();
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO update(AttachedTO to) throws SerException {
        Attached attached = super.findById(to.getId());
        LocalDateTime a = attached.getCreateTime();
        LocalDateTime b = attached.getModifyTime();
        attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached.setCreateTime(a);
        attached.setModifyTime(b);
        super.update(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }
}