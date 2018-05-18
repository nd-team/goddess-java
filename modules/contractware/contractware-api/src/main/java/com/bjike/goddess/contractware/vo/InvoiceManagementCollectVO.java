package com.bjike.goddess.contractware.vo;

import com.bjike.goddess.contractware.enums.InvoiceType;

/**
* 发票管理汇总表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InvoiceManagementCollectVO {
    /**
     * id
     */
    private String  id;
    /**
     * 地区
     */
    private String  area;

    /**
     * 合作单位
     */
    private String  cooperator;

    /**
     * 项目内部名称
     */
    private String  innerProject;

    /**
     * 开票公司
     */
    private String  makeInvoiceCompany;

    /**
     * 发票类型
     */
    private InvoiceType invoiceType;

    /**
     * 发票金额
     */
    private Double  invoiceMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCooperator() {
        return cooperator;
    }

    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
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

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }
}