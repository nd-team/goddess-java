package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import java.time.LocalDate;

/**
 * 年月存本月和上个月数据传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-09 11:23 ]
 * @Description: [ 年月存本月和上个月数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearsDTO extends BaseDTO {
    private LocalDate startDate;//开始日期
    private LocalDate endDate;//结束日期
    private String accountInformation;//账户信息
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

    public String getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(String accountInformation) {
        this.accountInformation = accountInformation;
    }
}