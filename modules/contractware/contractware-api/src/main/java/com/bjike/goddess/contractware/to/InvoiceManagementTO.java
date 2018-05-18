package com.bjike.goddess.contractware.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.contractware.enums.InvoiceType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "内部合同编号不能为空" ,groups = {ADD.class, EDIT.class})
    private String  internalContractNumber;


    /**
     * 合作单位
     */
    @NotBlank(message = "合作单位不能为空" ,groups = {ADD.class, EDIT.class})
    private String  cooperator;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空" ,groups = {ADD.class, EDIT.class})
    private String  area;

    /**
     * 项目内部名称
     */
    @NotBlank(message = "项目内部名称不能为空" ,groups = {ADD.class, EDIT.class})
    private String  innerProject;

    /**
     * 开票公司
     */
    @NotBlank(message = "开票公司不能为空" ,groups = {ADD.class, EDIT.class})
    private String  makeInvoiceCompany;

    /**
     * 发票类型
     */
    @NotNull(message = "发票类型不能为空" ,groups = {ADD.class, EDIT.class})
    private InvoiceType invoiceType;

    /**
     * 发票编号
     */
    @NotBlank(message = "发票编号不能为空" ,groups = {ADD.class, EDIT.class})
    private String  invoiceNumber;

    /**
     * 发票金额
     */
    @NotNull(message = "发票金额不能为空" ,groups = {ADD.class, EDIT.class})
    private Double  invoiceMoney;

    /**
     * 开票日期
     */
    @NotBlank(message = "开票日期不能为空" ,groups = {ADD.class, EDIT.class})
    private String  makeInvoiceDate;

    /**
     * 张数
     */
    @NotNull(message = "张数不能为空" ,groups = {ADD.class, EDIT.class})
    private Integer  amount;

    /**
     * 电子版
     */
    @NotNull(message = "电子版不能为空" ,groups = {ADD.class, EDIT.class})
    private Boolean  electronicEdition;

    /**
     * 预计到账时间
     */
    @NotBlank(message = "预计到账时间不能为空" ,groups = {ADD.class, EDIT.class})
    private String expectedTimeIntoAccount;

    /**
     * 是否到账
     */
    @NotNull(message = "是否到账不能为空" ,groups = {ADD.class, EDIT.class})
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