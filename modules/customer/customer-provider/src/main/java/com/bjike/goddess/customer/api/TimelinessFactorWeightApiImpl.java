package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.TimelinessFactorWeightBO;
import com.bjike.goddess.customer.service.TimelinessFactorWeightSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 时效性因素层权重业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("timelinessFactorWeightApiImpl")
public class TimelinessFactorWeightApiImpl implements TimelinessFactorWeightAPI {
    @Autowired
    private TimelinessFactorWeightSer timelinessFactorWeightSer;

    @Override
    public TimelinessFactorWeightBO findByName(String timelinessName) throws SerException {
        return timelinessFactorWeightSer.findByName(timelinessName);
    }
}