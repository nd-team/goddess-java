package com.bjike.goddess.contacts.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.entity.Commonality;
import com.bjike.goddess.contacts.to.CommonalityTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公共邮箱管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contactsSerCache")
@Service
public class CommonalitySerImpl extends ServiceImpl<Commonality, CommonalityDTO> implements CommonalitySer {


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO save(CommonalityTO to) throws SerException {
        Commonality entity = BeanTransform.copyProperties(to, Commonality.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO update(CommonalityTO to) throws SerException {
        Commonality entity = BeanTransform.copyProperties(to, Commonality.class), commonality = super.findById(to.getId());
        entity.setCreateTime(commonality.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO delete(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO congeal(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommonalityBO thaw(CommonalityTO to) throws SerException {
        Commonality entity = super.findById(to.getId());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }

    @Override
    public List<CommonalityBO> findThaw() throws SerException {
        CommonalityDTO dto = new CommonalityDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Commonality> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CommonalityBO.class);
    }

    @Override
    public List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        List<Commonality> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CommonalityBO.class);
    }

    @Override
    public CommonalityBO findByDepartment(String department) throws SerException {
        CommonalityDTO dto = new CommonalityDTO();
        dto.getConditions().add(Restrict.eq("department_id", department));
        Commonality entity = super.findOne(dto);
        return BeanTransform.copyProperties(entity, CommonalityBO.class);
    }
}