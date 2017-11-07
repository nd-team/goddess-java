package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.ClosenessFoactorWeightBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorWeightDTO;
import com.bjike.goddess.customer.entity.ClosenessFoactorWeight;

/**
 * 亲密度因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ClosenessFoactorWeightSer extends Ser<ClosenessFoactorWeight, ClosenessFoactorWeightDTO> {
    /**
     * 根据亲密度类型名称获取对应权重数据
     *
     * @param closenessName 亲密度类型名称
     */
    default ClosenessFoactorWeightBO findByName(String closenessName) throws SerException {
        return null;
    }
}