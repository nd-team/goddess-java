package com.bjike.goddess.contractware.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InvoiceCollectVO implements Serializable{
    /**
     * 发票金额合计
     */
    private Double invoiceMoneyTotal;

    /**
     * 子集
     */
    private List<InvoiceManagementCollectVO> invoiceManagementCollectList;

    public Double getInvoiceMoneyTotal() {
        return invoiceMoneyTotal;
    }

    public void setInvoiceMoneyTotal(Double invoiceMoneyTotal) {
        this.invoiceMoneyTotal = invoiceMoneyTotal;
    }

    public List<InvoiceManagementCollectVO> getInvoiceManagementCollectList() {
        return invoiceManagementCollectList;
    }

    public void setInvoiceManagementCollectList(List<InvoiceManagementCollectVO> invoiceManagementCollectList) {
        this.invoiceManagementCollectList = invoiceManagementCollectList;
    }
}
