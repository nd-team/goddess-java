package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.CustomerInfoBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfo;
import com.bjike.goddess.moneyside.to.CustomerInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CustomerInfoSerImpl extends ServiceImpl<CustomerInfo, CustomerInfoDTO> implements CustomerInfoSer {
    @Override
    public Long countCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        Long count = super.count(customerInfoDTO);
        return count;
    }

    @Override
    public CustomerInfoBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CustomerInfo customerInfo  = super.findById(id);
        return BeanTransform.copyProperties(customerInfo,CustomerInfoBO.class);
    }

    @Override
    public List<CustomerInfoBO> findListCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        List<CustomerInfo> customerInfos = super.findByPage(customerInfoDTO);
        List<CustomerInfoBO> customerInfoBOS = BeanTransform.copyProperties(customerInfos,CustomerInfoBO.class);
        return customerInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerInfoBO insertCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        CustomerInfo customerInfo = BeanTransform.copyProperties(customerInfoTO,CustomerInfo.class,true);
        customerInfo.setCreateTime(LocalDateTime.now());
        super.save(customerInfo);
        return BeanTransform.copyProperties(customerInfo,CustomerInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerInfoBO editCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        if(StringUtils.isBlank(customerInfoTO.getId())){
            throw new SerException("id不能为空");
        }
        CustomerInfo customerInfo = super.findById(customerInfoTO.getId());
        BeanTransform.copyProperties(customerInfoTO,customerInfo,true);
        customerInfo.setModifyTime(LocalDateTime.now());
        return BeanTransform.copyProperties(customerInfo,CustomerInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCustomerInfo(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}