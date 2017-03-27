package com.bjike.goddess.businessproject.entity;

import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 商务项目合同基本信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.340 ]
 * @Description: [ 商务项目合同基本信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessproject_baseinfomanage")
public class BaseInfoManage extends BaseEntity {

    /**
     * 合同档案编号
     */
    @Column(name = "contractNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '合同档案编号'")
    private String contractNum;

    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "INT(2)    COMMENT '业务类型'")
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessSubject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerProject", columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @Column(name = "outProjectNum", columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目编号'")
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @Column(name = "saleContractNum", columnDefinition = "VARCHAR(255)   COMMENT '对应销售合同编号'")
    private String saleContractNum;

    /**
     * 合作方式
     */
    @Column(name = "businessCooperate", columnDefinition = "INT(2)    COMMENT '合作方式'")
    private BusinessCooperate businessCooperate;

    /**
     * 内部项目名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerProject;

    /**
     * 内部项目编号
     */
    @Column(name = "innerProjectNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String innerProjectNum;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 所属项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectGroup;

    /**
     * 签订年份
     */
    @Column(name = "siginYear", columnDefinition = "VARCHAR(255)   COMMENT '签订年份'")
    private String siginYear;

    /**
     * 签订时间
     */
    @Column(name = "siginTime", columnDefinition = "DATE  COMMENT '签订时间'")
    private LocalDate siginTime;

    /**
     * 合同金额
     */
    @Column(name = "money", columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double money;

    /**
     * 开工时间
     */
    @Column(name = "startProjectTime", columnDefinition = "DATE  COMMENT '开工时间'")
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @Column(name = "endProjectTime", columnDefinition = "DATE  COMMENT '完工时间'")
    private LocalDate endProjectTime;

    /**
     * 合同期限
     */
    @Column(name = "contractRang", columnDefinition = "VARCHAR(255)   COMMENT '合同期限'")
    private String contractRang;

    /**
     * 甲方公司名称
     */
    @Column(name = "firstCompany", columnDefinition = "VARCHAR(255)   COMMENT '甲方公司名称'")
    private String firstCompany;

    /**
     * 甲方联系人
     */
    @Column(name = "firstRelation", columnDefinition = "VARCHAR(255)   COMMENT '甲方联系人'")
    private String firstRelation;

    /**
     * 甲方联系人电话
     */
    @Column(name = "firstTel", columnDefinition = "VARCHAR(255)   COMMENT '甲方联系人电话'")
    private String firstTel;

    /**
     * 乙方公司名称
     */
    @Column(name = "secondCompany", columnDefinition = "VARCHAR(255)   COMMENT '乙方公司名称'")
    private String secondCompany;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge", columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 项目负责人电话
     */
    @Column(name = "projectChargeTel", columnDefinition = "VARCHAR(255)   COMMENT '项目负责人电话'")
    private String projectChargeTel;

    /**
     * 客户名称
     */
    @Column(name = "customerName", columnDefinition = "VARCHAR(255)   COMMENT '客户名称'")
    private String customerName;

    /**
     * 合同内容
     */
    @Column(name = "contractText", columnDefinition = "VARCHAR(255)   COMMENT '合同内容'")
    private String contractText;

    /**
     * 税率
     */
    @Column(name = "rate", columnDefinition = "DECIMAL(10,2)   COMMENT '税率'")
    private Double rate;

    /**
     * 合同属性
     */
    @Column(name = "contractProperty", columnDefinition = "INT(2)   COMMENT '合同属性'")
    private ContractProperty contractProperty;

    /**
     * 支付方式
     */
    @Column(name = "payWays", columnDefinition = "INT(2)   COMMENT '支付方式'")
    private PayWays payWays;

    /**
     * 付款比例
     */
    @Column(name = "payRate", columnDefinition = "VARCHAR(255)   COMMENT '付款比例'")
    private String payRate;

    /**
     * 结算费用来源
     */
    @Column(name = "payFeeOrigin", columnDefinition = "INT(2)   COMMENT '结算费用来源'")
    private PayFeeOrigin payFeeOrigin;

    /**
     * 合同是否已归档
     */
    @Column(name = "fileCondition", columnDefinition = "VARCHAR(255)   COMMENT '合同是否已归档'")
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @Column(name = "fileCount", columnDefinition = "DECIMAL(10,2)   COMMENT '合同归档数量'")
    private Double fileCount;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

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

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getOutProjectNum() {
        return outProjectNum;
    }

    public void setOutProjectNum(String outProjectNum) {
        this.outProjectNum = outProjectNum;
    }

    public String getSaleContractNum() {
        return saleContractNum;
    }

    public void setSaleContractNum(String saleContractNum) {
        this.saleContractNum = saleContractNum;
    }

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getInnerProjectNum() {
        return innerProjectNum;
    }

    public void setInnerProjectNum(String innerProjectNum) {
        this.innerProjectNum = innerProjectNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getSiginYear() {
        return siginYear;
    }

    public void setSiginYear(String siginYear) {
        this.siginYear = siginYear;
    }

    public LocalDate getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(LocalDate siginTime) {
        this.siginTime = siginTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(LocalDate startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public LocalDate getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(LocalDate endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getContractRang() {
        return contractRang;
    }

    public void setContractRang(String contractRang) {
        this.contractRang = contractRang;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getFirstRelation() {
        return firstRelation;
    }

    public void setFirstRelation(String firstRelation) {
        this.firstRelation = firstRelation;
    }

    public String getFirstTel() {
        return firstTel;
    }

    public void setFirstTel(String firstTel) {
        this.firstTel = firstTel;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getProjectChargeTel() {
        return projectChargeTel;
    }

    public void setProjectChargeTel(String projectChargeTel) {
        this.projectChargeTel = projectChargeTel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractText() {
        return contractText;
    }

    public void setContractText(String contractText) {
        this.contractText = contractText;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public PayFeeOrigin getPayFeeOrigin() {
        return payFeeOrigin;
    }

    public void setPayFeeOrigin(PayFeeOrigin payFeeOrigin) {
        this.payFeeOrigin = payFeeOrigin;
    }

    public String getFileCondition() {
        return fileCondition;
    }

    public void setFileCondition(String fileCondition) {
        this.fileCondition = fileCondition;
    }

    public Double getFileCount() {
        return fileCount;
    }

    public void setFileCount(Double fileCount) {
        this.fileCount = fileCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}