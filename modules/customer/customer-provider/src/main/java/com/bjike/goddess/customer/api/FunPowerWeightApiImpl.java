package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.FunPowerWeightBO;
import com.bjike.goddess.customer.service.FunPowerWeightSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 职权因素层权重业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("funPowerWeightApiImpl")
public class FunPowerWeightApiImpl implements FunPowerWeightAPI {
    @Autowired
    private FunPowerWeightSer funPowerWeightSer;

    @Override
    public FunPowerWeightBO findByName(String funPowerTypeName) throws SerException {
        return funPowerWeightSer.findByName(funPowerTypeName);
    }
}