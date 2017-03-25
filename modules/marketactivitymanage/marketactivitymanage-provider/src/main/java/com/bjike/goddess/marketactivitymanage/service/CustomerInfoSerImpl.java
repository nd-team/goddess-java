package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户信息业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketactivitymanageSerCache")
@Service
public class CustomerInfoSerImpl extends ServiceImpl<CustomerInfo, CustomerInfoDTO> implements CustomerInfoSer {

    /**
     * 分页查询客户信息
     *
     * @param dto 客户信息dto
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public List<CustomerInfoBO> list(CustomerInfoDTO dto) throws SerException {
        List<CustomerInfo> list = super.findByPage(dto);
        List<CustomerInfoBO> boList = BeanTransform.copyProperties(list, CustomerInfoBO.class);
        return boList;
    }

    /**
     * 保存客户信息
     *
     * @param to 客户信息to
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    @Transactional
    public CustomerInfoBO save(CustomerInfoTO to) throws SerException {
        CustomerInfo entity = BeanTransform.copyProperties(to, CustomerInfo.class, true);
        entity = super.save(entity);
        CustomerInfoBO customerInfoBO = BeanTransform.copyProperties(entity, CustomerInfoBO.class);
        return customerInfoBO;
    }

    /**
     * 更新客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(CustomerInfoTO to) throws SerException {
        CustomerInfo customerInfo = BeanTransform.copyProperties(to, CustomerInfo.class, true);
        super.update(customerInfo);
    }

    /**
     * 根据id删除客户信息
     *
     * @param id 客户信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}