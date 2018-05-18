package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.FirstFactorWeightBO;
import com.bjike.goddess.customer.dto.FirstFactorWeightDTO;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 一层因素层权重业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:28 ]
 * @Description: [ 一层因素层权重业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class FirstFactorWeightSerImpl extends ServiceImpl<FirstFactorWeight, FirstFactorWeightDTO> implements FirstFactorWeightSer {
    @Override
    public FirstFactorWeightBO findByName(String firstFactorName) throws SerException {
        FirstFactorWeightDTO firstFactorWeightDTO = new FirstFactorWeightDTO();
        firstFactorWeightDTO.getConditions().add(Restrict.eq("firstFactorName",firstFactorName));
        FirstFactorWeight firstFactorWeight = super.findOne(firstFactorWeightDTO);
        return BeanTransform.copyProperties(firstFactorWeight,FirstFactorWeightBO.class);
    }
}