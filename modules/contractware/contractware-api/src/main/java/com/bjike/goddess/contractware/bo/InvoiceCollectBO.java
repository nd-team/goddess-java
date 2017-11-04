package com.bjike.goddess.contractware.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 10:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InvoiceCollectBO implements Serializable{
    /**
     * 发票金额合计
     */
    private Double invoiceMoneyTotal;

    /**
     * 基础子集
     */
    private List<InvoiceManagementCollectBO> invoiceManagementCollectList;

    public Double getInvoiceMoneyTotal() {
        return invoiceMoneyTotal;
    }

    public void setInvoiceMoneyTotal(Double invoiceMoneyTotal) {
        this.invoiceMoneyTotal = invoiceMoneyTotal;
    }

    public List<InvoiceManagementCollectBO> getInvoiceManagementCollectList() {
        return invoiceManagementCollectList;
    }

    public void setInvoiceManagementCollectList(List<InvoiceManagementCollectBO> invoiceManagementCollectList) {
        this.invoiceManagementCollectList = invoiceManagementCollectList;
    }
}
