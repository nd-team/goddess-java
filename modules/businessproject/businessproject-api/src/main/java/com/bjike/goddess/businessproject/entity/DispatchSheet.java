package com.bjike.goddess.businessproject.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 商务项目派工单信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessproject_dispatchsheet")
public class DispatchSheet extends BaseEntity {

    /**
     * 内部项目编号
     */
    @Column(name = "innerProjectNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目编号'")
    private String innerProjectNum;

    /**
     * 业务类型
     */
    @Column(name = "businessType",  columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessSubject",  columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessSubject;

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerProject",  columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerProject;

    /**
     * 合同外部项目编号
     */
    @Column(name = "outProjectNum",  columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目编号'")
    private String outProjectNum;

    /**
     * 对应销售合同编号
     */
    @Column(name = "saleContractNum",  columnDefinition = "VARCHAR(255)   COMMENT '对应销售合同编号'")
    private String saleContractNum;

    /**
     * 合作方式
     */
    @Column(name = "businessCooperate",  columnDefinition = "VARCHAR(255)   COMMENT '合作方式'")
    private String businessCooperate;

    /**
     * 内部项目名称
     */
    @Column(name = "innerProject",  columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerProject;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 所属项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String projectGroup;

    /**
     * 签订时间
     */
    @Column(name = "siginTime",  columnDefinition = "DATE   COMMENT '签订时间'")
    private LocalDate siginTime;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge",  columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 派工单名称
     */
    @Column(name = "dispatchProject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单名称'")
    private String dispatchProject;

    /**
     * 派工单编号
     */
    @Column(name = "dispatchNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String dispatchNum;

    /**
     * 总包单位名称
     */
    @Column(name = "majorCompany", columnDefinition = "VARCHAR(255)   COMMENT '总包单位名称'")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @Column(name = "subCompany",  columnDefinition = "VARCHAR(255)   COMMENT '分包单位名称'")
    private String subCompany;

    /**
     * 客户名称
     */
    @Column(name = "customerName",  columnDefinition = "VARCHAR(255)   COMMENT '客户名称'")
    private String customerName;

    /**
     * 派工内容
     */
    @Column(name = "dispatchText",  columnDefinition = "MEDIUMTEXT   COMMENT '派工内容'")
    private String dispatchText;

    /**
     * 开工日期
     */
    @Column(name = "startProjectTime", columnDefinition = "DATE   COMMENT '开工日期'")
    private LocalDate startProjectTime;

    /**
     * 完工日期
     */
    @Column(name = "endProjectTime",columnDefinition = "DATE   COMMENT '完工日期'")
    private LocalDate endProjectTime;

    /**
     * 派工金额
     */
    @Column(name = "money",  columnDefinition = "DECIMAL(10,2)   COMMENT '派工金额'")
    private Double money;

    /**
     * 是否完工
     */
    @Column(name = "completeProject",  columnDefinition = "VARCHAR(255)   COMMENT '是否完工'")
    private String completeProject;

    /**
     * 合同是否已归档
     */
    @Column(name = "fileCondition",  columnDefinition = "VARCHAR(255)   COMMENT '合同是否已归档'")
    private String fileCondition;

    /**
     * 合同归档数量
     */
    @Column(name = "fileCount",  columnDefinition = "DECIMAL(10,2)   COMMENT '合同归档数量'")
    private Double fileCount;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "MEDIUMTEXT   COMMENT '备注'")
    private String remark;

    /**
     * 临时合同编号
     */
    @Column(name = "tempContractNum", columnDefinition = "VARCHAR(255)   COMMENT '临时合同编号'")
    private String tempContractNum;


    public String getInnerProjectNum() {
        return innerProjectNum;
    }

    public void setInnerProjectNum(String innerProjectNum) {
        this.innerProjectNum = innerProjectNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
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

    public String getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(String businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
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

    public LocalDate getSiginTime() {
        return siginTime;
    }

    public void setSiginTime(LocalDate siginTime) {
        this.siginTime = siginTime;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getDispatchProject() {
        return dispatchProject;
    }

    public void setDispatchProject(String dispatchProject) {
        this.dispatchProject = dispatchProject;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDispatchText() {
        return dispatchText;
    }

    public void setDispatchText(String dispatchText) {
        this.dispatchText = dispatchText;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCompleteProject() {
        return completeProject;
    }

    public void setCompleteProject(String completeProject) {
        this.completeProject = completeProject;
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

    public String getTempContractNum() {
        return tempContractNum;
    }

    public void setTempContractNum(String tempContractNum) {
        this.tempContractNum = tempContractNum;
    }
}