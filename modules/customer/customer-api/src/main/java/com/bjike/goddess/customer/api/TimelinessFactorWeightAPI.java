package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.TimelinessFactorWeightBO;

/**
 * 时效性因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimelinessFactorWeightAPI {
    /**
     * 根据职权类型名称获取对应权重数据
     *
     * @param timelinessName 时效性类型名称
     */
    default TimelinessFactorWeightBO findByName(String timelinessName) throws SerException {
        return null;
    }
}