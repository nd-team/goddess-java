package com.bjike.goddess.businessproject.vo;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 合同结算过程信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractAccountVO {

    private String id;

    /**
     * 是否正在走结算流程
     */
    private Boolean settlementProcess;

    /**
     * 是否到账
     */
    private Boolean account;

    /**
     * 是否闭单
     */
    private Boolean closeSingle;

    /**
     * 合作方式
     */
    private String businessCooperate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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