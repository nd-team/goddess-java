package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.ClosenessFoactorWeightBO;

/**
 * 亲密度因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ClosenessFoactorWeightAPI {
    /**
     * 根据亲密度类型名称获取对应权重数据
     *
     * @param closenessName 亲密度类型名称
     */
    default ClosenessFoactorWeightBO findByName(String closenessName) throws SerException {
        return null;
    }
}