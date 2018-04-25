package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 银行流水数据传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:27 ]
 * @Description: [ 银行流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankSummaryDTO extends BaseDTO {

    private String startDate;//开始日期

    private String endDate;//结束日期

    private String accountId;//账户信息

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BankSummaryDTO() {
    }
}