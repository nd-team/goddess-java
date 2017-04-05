package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.SupplierTypeBO;
import com.bjike.goddess.supplier.dto.SupplierTypeDTO;
import com.bjike.goddess.supplier.entity.SupplierType;
import com.bjike.goddess.supplier.to.SupplierTypeTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商类型管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:55 ]
 * @Description: [ 供应商类型管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierTypeSerImpl extends ServiceImpl<SupplierType, SupplierTypeDTO> implements SupplierTypeSer {

    @Override
    public List<SupplierTypeBO> findStatus() throws SerException {
        SupplierTypeDTO dto = new SupplierTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<SupplierType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO save(SupplierTypeTO to) throws SerException {
        SupplierType entity = BeanTransform.copyProperties(to, SupplierType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO update(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        entity.setName(to.getName());
        entity.setDescription(to.getDescription());
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO delete(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO congeal(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO thaw(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }
}