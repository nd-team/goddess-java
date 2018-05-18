package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.FunPowerWeightBO;
import com.bjike.goddess.customer.dto.FunPowerWeightDTO;
import com.bjike.goddess.customer.entity.FunPowerWeight;

import java.util.List;

/**
 * 职权因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FunPowerWeightSer extends Ser<FunPowerWeight, FunPowerWeightDTO> {
    /**
     * 根据职权类型名称获取对应权重数据
     *
     * @param funPowerTypeName 职权类型名称
     */
    default FunPowerWeightBO findByName(String funPowerTypeName) throws SerException {
        return null;
    }
}