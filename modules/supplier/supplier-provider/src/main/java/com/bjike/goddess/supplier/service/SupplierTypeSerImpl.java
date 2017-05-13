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
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SupplierType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO delete(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO congeal(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierTypeBO thaw(SupplierTypeTO to) throws SerException {
        SupplierType entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }

    @Override
    public List<SupplierTypeBO> maps(SupplierTypeDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        List<SupplierType> list = super.findByPage(dto);
        if (null != list && list.size() > 0)
            return BeanTransform.copyProperties(list, SupplierTypeBO.class);
        else
            return new ArrayList<SupplierTypeBO>(0);
    }

    @Override
    public SupplierTypeBO getById(String id) throws SerException {
        SupplierType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        return BeanTransform.copyProperties(entity, SupplierTypeBO.class);
    }
}