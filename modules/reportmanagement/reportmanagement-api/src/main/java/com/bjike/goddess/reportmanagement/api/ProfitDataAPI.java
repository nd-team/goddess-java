package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.entity.ProfitData;

/**
 * 补充资料业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 11:54 ]
 * @Description: [ 补充资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProfitDataAPI {

    void save(ProfitData profit) throws SerException;
}