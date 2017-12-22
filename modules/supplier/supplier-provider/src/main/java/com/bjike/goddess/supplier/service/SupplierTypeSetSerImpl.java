package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.SupplierTypeSetBO;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.entity.SupplierTypeSet;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商类型设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierTypeSetSerImpl extends ServiceImpl<SupplierTypeSet, SupplierTypeSetDTO> implements SupplierTypeSetSer {
    @Override
    public Long countSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        Long count = super.count(supplierTypeSetDTO);
        return count;
    }

    @Override
    public SupplierTypeSetBO getOneById(String id) throws SerException {
        SupplierTypeSet supplierTypeSet = super.findById(id);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public List<SupplierTypeSetBO> listSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        List<SupplierTypeSet> supplierTypeSetList = super.findByCis(supplierTypeSetDTO, true);
        return BeanTransform.copyProperties(supplierTypeSetList, SupplierTypeSetBO.class);
    }

    @Override
    public SupplierTypeSetBO addSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        SupplierTypeSet supplierTypeSet = BeanTransform.copyProperties(supplierTypeSetTO, SupplierTypeSet.class);
        supplierTypeSet.setCreateTime(LocalDateTime.now());
        super.save(supplierTypeSet);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public SupplierTypeSetBO editSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        SupplierTypeSet supplierTypeSet = super.findById(supplierTypeSetTO.getId());
        LocalDateTime dateTime = supplierTypeSet.getCreateTime();
        supplierTypeSet = BeanTransform.copyProperties(supplierTypeSetTO, SupplierTypeSet.class, true);
        supplierTypeSet.setCreateTime(dateTime);
        supplierTypeSet.setModifyTime(LocalDateTime.now());
        super.update(supplierTypeSet);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<String> findAllType() throws SerException {
        List<SupplierTypeSet> supplierTypeSetList = super.findAll();
        if(CollectionUtils.isEmpty(supplierTypeSetList)){
            return Collections.emptyList();
        }
     return supplierTypeSetList.stream().map(SupplierTypeSet::getSupplierType).distinct().collect(Collectors.toList());
    }
}