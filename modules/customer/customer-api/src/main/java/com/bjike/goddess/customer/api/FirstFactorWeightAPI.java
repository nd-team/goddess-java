package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.FirstFactorWeightBO;

/**
 * 一层因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:28 ]
 * @Description: [ 一层因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstFactorWeightAPI {
    /**
     * 根据一层因素名获取对应权重数据
     *
     * @param firstFactorName 一层因素名
     */
    default FirstFactorWeightBO findByName(String firstFactorName) throws SerException {
        return null;
    }
}