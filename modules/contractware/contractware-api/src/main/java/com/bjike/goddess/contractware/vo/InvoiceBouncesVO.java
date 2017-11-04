package com.bjike.goddess.contractware.vo;

import com.bjike.goddess.contractware.bo.InvoiceManagementBO;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 11:57]
 * @Description: [弹框]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InvoiceBouncesVO {
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
    private List<InvoiceManagementVO> invoiceManagementList;

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

    public List<InvoiceManagementVO> getInvoiceManagementList() {
        return invoiceManagementList;
    }

    public void setInvoiceManagementList(List<InvoiceManagementVO> invoiceManagementList) {
        this.invoiceManagementList = invoiceManagementList;
    }
}
