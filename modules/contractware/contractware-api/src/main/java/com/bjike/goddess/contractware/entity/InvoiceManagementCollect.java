package com.bjike.goddess.contractware.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 发票管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "contractware_invoicemanagementcollect")
public class InvoiceManagementCollect extends BaseEntity {
    /**
     * 地区
     */
    @Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 合作单位
     */
    @Column(name = "cooperator",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '合作单位'"  )
    private String  cooperator;

    /**
     * 项目内部名称
     */
    @Column(name = "innerProject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'"  )
    private String  innerProject;

    /**
     * 开票公司
     */
    @Column(name = "makeInvoiceCompany",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '开票公司'"  )
    private String  makeInvoiceCompany;

    /**
     * 发票类型
     */
    @Column(name = "invoiceType",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '发票类型'"  )
    private String  invoiceType;

    /**
     * 发票金额
     */
    @Column(name = "invoiceMoney",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '发票金额'"  )
    private Double  invoiceMoney;

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

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }
}