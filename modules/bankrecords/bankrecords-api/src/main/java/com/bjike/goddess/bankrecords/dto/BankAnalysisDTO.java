package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 银行流水分析数据传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-09 09:42 ]
 * @Description: [ 银行流水分析数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankAnalysisDTO extends BaseDTO {
    @NotNull(message = "开始时间不能空")
    private LocalDate startDate;//开始日期
    @NotNull(message = "结束时间不能空")
    private LocalDate endDate;//结束日期
    private String accountId;//账户信息

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}