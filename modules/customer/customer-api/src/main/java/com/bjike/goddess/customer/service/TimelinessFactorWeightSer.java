package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.TimelinessFactorWeightBO;
import com.bjike.goddess.customer.dto.TimelinessFactorWeightDTO;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;

/**
 * 时效性因素层权重业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimelinessFactorWeightSer extends Ser<TimelinessFactorWeight, TimelinessFactorWeightDTO> {
    /**
     * 根据职权类型名称获取对应权重数据
     *
     * @param timelinessName 时效性类型名称
     */
    default TimelinessFactorWeightBO findByName(String timelinessName) throws SerException {
        return null;
    }
}