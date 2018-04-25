package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialbuy.dto.BankSummaryDTO;
import com.bjike.goddess.materialbuy.entity.BankSummary;

/**
 * 对银行流水汇总业务接口
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-02 02:21 ]
 * @Description: [ 对银行流水汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankSummarySer extends Ser<BankSummary, BankSummaryDTO> {
    /**
     * 把银行流水增加到汇总
     */
    void summaryData() throws SerException;
}