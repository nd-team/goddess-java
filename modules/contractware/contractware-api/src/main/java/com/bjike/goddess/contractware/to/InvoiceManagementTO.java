package com.bjike.goddess.contractware.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.contractware.enums.InvoiceType;

import java.time.LocalDate;

/**
* 发票管理
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InvoiceManagementTO extends BaseTO {
    /**
     * 内部合同编号
     */
    private String  internalContractNumber;


    /**
     * 合作单位
     */
    private String  cooperator;

    /**
     * 地区
     */
    private String  area;

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
     * 发票编号
     */
    private String  invoiceNumber;

    /**
     * 发票金额
     */
    private Double  invoiceMoney;

    /**
     * 开票日期
     */
    private LocalDate  makeInvoiceDate;

    /**
     * 张数
     */
    private Integer  amount;

    /**
     * 电子版
     */
    private Boolean  electronicEdition;

    /**
     * 预计到账时间
     */
    private LocalDate expectedTimeIntoAccount;

    /**
     * 是否到账
     */
    private Boolean  ifReceiveMoney;

    public String getInternalContractNumber() {
        return internalContractNumber;
    }

    public void setInternalContractNumber(String internalContractNumber) {
        this.internalContractNumber = internalContractNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public LocalDate getMakeInvoiceDate() {
        return makeInvoiceDate;
    }

    public void setMakeInvoiceDate(LocalDate makeInvoiceDate) {
        this.makeInvoiceDate = makeInvoiceDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getElectronicEdition() {
        return electronicEdition;
    }

    public void setElectronicEdition(Boolean electronicEdition) {
        this.electronicEdition = electronicEdition;
    }

    public LocalDate getExpectedTimeIntoAccount() {
        return expectedTimeIntoAccount;
    }

    public void setExpectedTimeIntoAccount(LocalDate expectedTimeIntoAccount) {
        this.expectedTimeIntoAccount = expectedTimeIntoAccount;
    }

    public Boolean getIfReceiveMoney() {
        return ifReceiveMoney;
    }

    public void setIfReceiveMoney(Boolean ifReceiveMoney) {
        this.ifReceiveMoney = ifReceiveMoney;
    }

    public String getCooperator() {
        return cooperator;
    }

    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }
}