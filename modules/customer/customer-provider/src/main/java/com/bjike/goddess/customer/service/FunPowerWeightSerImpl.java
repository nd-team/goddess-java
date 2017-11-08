package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.FunPowerWeightBO;
import com.bjike.goddess.customer.dto.FunPowerWeightDTO;
import com.bjike.goddess.customer.entity.FunPowerWeight;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 职权因素层权重业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class FunPowerWeightSerImpl extends ServiceImpl<FunPowerWeight, FunPowerWeightDTO> implements FunPowerWeightSer {
    @Override
    public FunPowerWeightBO findByName(String funPowerTypeName) throws SerException {
        FunPowerWeightDTO funPowerWeightDTO = new FunPowerWeightDTO();
        funPowerWeightDTO.getConditions().add(Restrict.eq("funPowerTypeName",funPowerTypeName));
        FunPowerWeight funPowerWeight = super.findOne(funPowerWeightDTO);
        return BeanTransform.copyProperties(funPowerWeight,FunPowerWeightBO.class);
    }
}