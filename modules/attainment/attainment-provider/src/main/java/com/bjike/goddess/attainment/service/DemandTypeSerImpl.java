package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.DemandTypeBO;
import com.bjike.goddess.attainment.dto.DemandTypeDTO;
import com.bjike.goddess.attainment.entity.DemandType;
import com.bjike.goddess.attainment.to.DemandTypeTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研需求类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class DemandTypeSerImpl extends ServiceImpl<DemandType, DemandTypeDTO> implements DemandTypeSer {

    @Override
    public DemandTypeBO save(DemandTypeTO to) throws SerException {
        DemandType entity = BeanTransform.copyProperties(to, DemandType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DemandTypeBO.class);
    }

    @Override
    public DemandTypeBO update(DemandTypeTO to) throws SerException {
        DemandType entity = BeanTransform.copyProperties(to, DemandType.class), type = super.findById(to.getId());
        if (null == type)
            throw new SerException("程序错误,请刷新重试");
        entity.setStatus(type.getStatus());
        entity.setCreateTime(type.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, DemandTypeBO.class);
    }

    @Override
    public DemandTypeBO delete(String id) throws SerException {
        DemandType entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DemandTypeBO.class);
    }

    @Override
    public DemandTypeBO congeal(String id) throws SerException {
        DemandType entity = super.findById(id);
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, DemandTypeBO.class);
    }

    @Override
    public DemandTypeBO thaw(String id) throws SerException {
        DemandType entity = super.findById(id);
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, DemandTypeBO.class);
    }

    @Override
    public List<DemandTypeBO> findThaw() throws SerException {
        DemandTypeDTO dto = new DemandTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<DemandType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandTypeBO.class);
    }
}