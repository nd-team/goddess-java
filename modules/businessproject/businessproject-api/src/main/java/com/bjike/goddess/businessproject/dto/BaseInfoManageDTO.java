package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.businessproject.enums.PayWays;
import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务项目合同基本信息管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.344 ]
 * @Description: [ 商务项目合同基本信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseInfoManageDTO extends BaseDTO {

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 合作方式
     */
    private BusinessCooperate businessCooperate;

    /**
     * 甲方公司名称
     */
    private String firstCompany;

    /**
     * 乙方公司名称
     */
    private String secondCompany;
    /**
     * 地区
     */
    private String area;
    /**
     * 合同属性
     */
    private ContractProperty contractProperty;

    /**
     * 支付方式
     */
    private PayWays payWays;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 签订年份
     */
    private String siginYear;

    /**
     * 合同是否已归档
     */
    private String fileCondition;

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public PayWays getPayWays() {
        return payWays;
    }

    public void setPayWays(PayWays payWays) {
        this.payWays = payWays;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSiginYear() {
        return siginYear;
    }

    public void setSiginYear(String siginYear) {
        this.siginYear = siginYear;
    }

    public String getFileCondition() {
        return fileCondition;
    }

    public void setFileCondition(String fileCondition) {
        this.fileCondition = fileCondition;
    }
}