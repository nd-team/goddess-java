package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-08 13:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RenewEnsureTO extends BaseTO{
    /**
     * 是否续签
     */
    private Boolean ifRenew;

    /**
     * 是否需要合同变更
     */
    private Boolean ifNeedContractChange;

    /**
     * 续签开始日期
     */
    private String renewStartDate;

    /**
     * 续签到期日期
     */
    private String renewEndDate;

    public Boolean getIfRenew() {
        return ifRenew;
    }

    public void setIfRenew(Boolean ifRenew) {
        this.ifRenew = ifRenew;
    }

    public Boolean getIfNeedContractChange() {
        return ifNeedContractChange;
    }

    public void setIfNeedContractChange(Boolean ifNeedContractChange) {
        this.ifNeedContractChange = ifNeedContractChange;
    }

    public String getRenewStartDate() {
        return renewStartDate;
    }

    public void setRenewStartDate(String renewStartDate) {
        this.renewStartDate = renewStartDate;
    }

    public String getRenewEndDate() {
        return renewEndDate;
    }

    public void setRenewEndDate(String renewEndDate) {
        this.renewEndDate = renewEndDate;
    }
}
