package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerContactWeightSetBO;
import com.bjike.goddess.customer.dto.CustomerContactWeightSetDTO;
import com.bjike.goddess.customer.entity.CustomerContactWeightSet;
import com.bjike.goddess.customer.to.CustomerContactWeightSetTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户接触阶段权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerContactWeightSetSerImpl extends ServiceImpl<CustomerContactWeightSet, CustomerContactWeightSetDTO> implements CustomerContactWeightSetSer {
    @Override
    public Long countContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        Long count = super.count(customerContactWeightSetDTO);
        return count;
    }

    @Override
    public CustomerContactWeightSetBO getOneContactWeight(String id) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = super.findById(id);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSet.class);
    }

    @Override
    public List<CustomerContactWeightSetBO> listContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        List<CustomerContactWeightSet> customerContactWeightSetList = super.findByCis(customerContactWeightSetDTO, true);
        return BeanTransform.copyProperties(customerContactWeightSetList, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerContactWeightSetBO addContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = BeanTransform.copyProperties(customerContactWeightSetTO, CustomerContactWeightSet.class, true);
        customerContactWeightSet.setCreateTime(LocalDateTime.now());
        super.save(customerContactWeightSet);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerContactWeightSetBO editContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        CustomerContactWeightSet customerContactWeightSet = super.findById(customerContactWeightSetTO.getId());
        LocalDateTime dateTime = customerContactWeightSet.getCreateTime();
        customerContactWeightSet = BeanTransform.copyProperties(customerContactWeightSetTO, CustomerContactWeightSet.class, true);
        customerContactWeightSet.setCreateTime(dateTime);
        super.update(customerContactWeightSet);
        return BeanTransform.copyProperties(customerContactWeightSet, CustomerContactWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteContactWeight(String id) throws SerException {
        super.remove(id);
    }
}