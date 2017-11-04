package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contractware.enums.InvoiceType;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 发票管理业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class InvoiceManagementBO extends BaseBO {

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 内部合同编号
     */
    private String  internalContractNumber;

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
    private String  makeInvoiceDate;

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
    private String expectedTimeIntoAccount;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMakeInvoiceDate() {
        return makeInvoiceDate;
    }

    public void setMakeInvoiceDate(String makeInvoiceDate) {
        this.makeInvoiceDate = makeInvoiceDate;
    }

    public String getExpectedTimeIntoAccount() {
        return expectedTimeIntoAccount;
    }

    public void setExpectedTimeIntoAccount(String expectedTimeIntoAccount) {
        this.expectedTimeIntoAccount = expectedTimeIntoAccount;
    }
}