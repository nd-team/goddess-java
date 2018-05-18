package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.entity.ProfitData;
import com.bjike.goddess.reportmanagement.service.ProfitDataSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 补充资料业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 11:54 ]
 * @Description: [ 补充资料业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("profitDataApiImpl")
public class ProfitDataApiImpl implements ProfitDataAPI {

    @Autowired
    ProfitDataSer profitDataSer;
    @Override
    public void save(ProfitData profit) throws SerException {
        profitDataSer.save(profit);
    }
}