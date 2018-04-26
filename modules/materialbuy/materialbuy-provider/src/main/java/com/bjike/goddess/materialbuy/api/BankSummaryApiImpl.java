package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialbuy.service.BankSummarySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对银行流水汇总业务接口实现
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-02 02:21 ]
 * @Description: [ 对银行流水汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankSummaryApiImpl")
public class BankSummaryApiImpl implements BankSummaryAPI {
    @Autowired
    private BankSummarySer bankSummarySer;

    @Override
    public void summaryData() throws SerException {
        bankSummarySer.summaryData();
    }

}