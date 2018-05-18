package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contractware.enums.InvoiceType;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 11:57]
 * @Description: [弹框]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InvoiceBouncesBO extends BaseBO {
    /**
     * 已开发票金额汇总
     */
    private Double invoiceAmount;

    /**
     * 合同发票金额
     */
    private Double contractInvoiceMoney;

    /**
     * 工程奖罚款
     */
    private Double engineeringAward;

    /**
     * 待开发票金额
     */
    private Double waitMakeInvoiceMoney;

    /**
     * 发票管理信息
     */
    private List<InvoiceManagementBO> invoiceManagementList;

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getContractInvoiceMoney() {
        return contractInvoiceMoney;
    }

    public void setContractInvoiceMoney(Double contractInvoiceMoney) {
        this.contractInvoiceMoney = contractInvoiceMoney;
    }

    public Double getEngineeringAward() {
        return engineeringAward;
    }

    public void setEngineeringAward(Double engineeringAward) {
        this.engineeringAward = engineeringAward;
    }

    public Double getWaitMakeInvoiceMoney() {
        return waitMakeInvoiceMoney;
    }

    public void setWaitMakeInvoiceMoney(Double waitMakeInvoiceMoney) {
        this.waitMakeInvoiceMoney = waitMakeInvoiceMoney;
    }

    public List<InvoiceManagementBO> getInvoiceManagementList() {
        return invoiceManagementList;
    }

    public void setInvoiceManagementList(List<InvoiceManagementBO> invoiceManagementList) {
        this.invoiceManagementList = invoiceManagementList;
    }
}
