package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.FirstFactorWeightBO;
import com.bjike.goddess.customer.service.FirstFactorWeightSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 一层因素层权重业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:28 ]
 * @Description: [ 一层因素层权重业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("firstFactorWeightApiImpl")
public class FirstFactorWeightApiImpl implements FirstFactorWeightAPI {
    @Autowired
    private FirstFactorWeightSer firstFactorWeightSer;
    @Override
    public FirstFactorWeightBO findByName(String firstFactorName) throws SerException {
        return firstFactorWeightSer.findByName(firstFactorName);
    }
}