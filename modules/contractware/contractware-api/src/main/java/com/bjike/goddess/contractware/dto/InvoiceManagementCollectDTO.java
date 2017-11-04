package com.bjike.goddess.contractware.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.contractware.enums.InvoiceType;

/**
* 发票管理汇总数据传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InvoiceManagementCollectDTO extends BaseDTO {
    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 开票公司
     */
    private String makeInvoiceCompany;

    /**
     * 开票类型
     */
    private InvoiceType invoiceType;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getMakeInvoiceCompany() {
        return makeInvoiceCompany;
    }

    public void setMakeInvoiceCompany(String makeInvoiceCompany) {
        this.makeInvoiceCompany = makeInvoiceCompany;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
}