package com.bjike.goddess.receivable.vo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-6.
 */
public class CollectContractorVO implements Serializable{
    /**
     * 总包单位
     */
    private String contractor;
    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 到账金额
     */
    private Double accountMoney;
    /**
     * 税金
     */
    private Double taxes;
    /**
     * 税后金额
     */
    private Double afterTax;

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }
}
