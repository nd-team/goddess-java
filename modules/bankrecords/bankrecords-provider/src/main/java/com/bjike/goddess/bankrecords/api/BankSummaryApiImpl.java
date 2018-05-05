package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.BankSummaryBO;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.service.BankSummarySer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 银行流水业务接口实现
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:27 ]
 * @Description: [ 银行流水业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankSummaryApiImpl")
public class BankSummaryApiImpl implements BankSummaryAPI {
    @Autowired
    private BankSummarySer bankSummarySer;

    @Override
    public List<BankSummaryBO> backfilterQuery(BankSummaryDTO dto,String[] accountIds) throws SerException {
        return bankSummarySer.backfilterQuery(dto,accountIds);
    }
}