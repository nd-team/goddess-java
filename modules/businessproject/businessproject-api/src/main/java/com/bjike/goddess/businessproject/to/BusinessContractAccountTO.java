package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 合同结算过程信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractAccountTO extends BaseTO {

    /**
     * 是否正在走结算流程
     */
    @NotNull(message = "是否正在走结算流程",groups = {EDIT.class})
    private Boolean settlementProcess;

    /**
     * 是否到账
     */
    @NotNull(message = "是否到账",groups = {EDIT.class})
    private Boolean account;

    /**
     * 是否闭单
     */
    @NotNull(message = "是否闭单",groups = {EDIT.class})
    private Boolean closeSingle;

    /**
     * 合作方式
     */
    @NotNull(message = "合作方式",groups = {EDIT.class})
    private String businessCooperate;

    public Boolean getSettlementProcess() {
        return settlementProcess;
    }

    public void setSettlementProcess(Boolean settlementProcess) {
        this.settlementProcess = settlementProcess;
    }

    public Boolean getAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public Boolean getCloseSingle() {
        return closeSingle;
    }

    public void setCloseSingle(Boolean closeSingle) {
        this.closeSingle = closeSingle;
    }

    public String getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(String businessCooperate) {
        this.businessCooperate = businessCooperate;
    }
}