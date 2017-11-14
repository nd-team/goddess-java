package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-08 14:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RelieveContractTO extends BaseTO{
    /**
     * 是否解除合同
     */
    private Boolean ifRelieveContract;

    /**
     * 合同解除时间
     */
    private String relieveDate;

    public Boolean getIfRelieveContract() {
        return ifRelieveContract;
    }

    public void setIfRelieveContract(Boolean ifRelieveContract) {
        this.ifRelieveContract = ifRelieveContract;
    }

    public String getRelieveDate() {
        return relieveDate;
    }

    public void setRelieveDate(String relieveDate) {
        this.relieveDate = relieveDate;
    }
}
