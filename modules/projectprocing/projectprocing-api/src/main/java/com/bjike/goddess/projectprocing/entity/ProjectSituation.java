package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 项目情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_projectsituation")
public class ProjectSituation extends BaseEntity {

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerName;

    /**
     * 内部项目名称
     */
    @Column(name = "innerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerName;

    /**
     * 合同签订情况
     */
    @Column(name = "signCondition",  columnDefinition = "VARCHAR(255)   COMMENT '合同签订情况'")
    private String signCondition;

    /**
     * 销售合同号
     */
    @Column(name = "saleNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String saleNum;

    /**
     * 派工单编号
     */
    @Column(name = "depatchNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String depatchNum;

    /**
     * 开工日期
     */
    @Column(name = "startWorkTime",  columnDefinition = "DATE  COMMENT '开工日期'")
    private LocalDate startWorkTime;

    /**
     * 计划完工日期
     */
    @Column(name = "planCompleteTime",  columnDefinition = "DATE   COMMENT '计划完工日期'")
    private LocalDate planCompleteTime;

    /**
     * 客户名称
     */
    @Column(name = "customerName",  columnDefinition = "VARCHAR(255)   COMMENT '客户名称'")
    private String customerName;

    /**
     * 客户负责人
     */
    @Column(name = "customerCharger", columnDefinition = "VARCHAR(255)   COMMENT '客户负责人'")
    private String customerCharger;

    /**
     * 客户联系电话
     */
    @Column(name = "customerTel",  columnDefinition = "VARCHAR(255)   COMMENT '客户联系电话'")
    private String customerTel;

    /**
     * 施工单位
     */
    @Column(name = "constructionUnit",  columnDefinition = "VARCHAR(255)   COMMENT '施工单位'")
    private String constructionUnit;

    /**
     * 工程督导
     */
    @Column(name = "enginSupervision",  columnDefinition = "VARCHAR(255)   COMMENT '工程督导'")
    private String enginSupervision;

    /**
     * 工程地点
     */
    @Column(name = "enginPlace", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工程地点'")
    private String enginPlace;

    /**
     * 项目经理
     */
    @Column(name = "projectManager",  columnDefinition = "VARCHAR(255)   COMMENT '项目经理'")
    private String projectManager;

    /**
     * 内容
     */
    @Column(name = "contents",  columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String contents;

    /**
     * 完工时间
     */
    @Column(name = "completeTime",  columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate completeTime;

    /**
     * 完工情况
     */
    @Column(name = "completeCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '完工情况'")
    private String completeCondition;

    /**
     * 完工报告
     */
    @Column(name = "completeReport", columnDefinition = "VARCHAR(255)   COMMENT '完工报告'")
    private String completeReport;

    /**
     * 合同跟进
     */
    @Column(name = "contractFollow", columnDefinition = "VARCHAR(255)   COMMENT '合同跟进'")
    private String contractFollow;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 商务合同外部项目名称id
     */
    @Column(name = "outerNameId",  columnDefinition = "VARCHAR(255)   COMMENT '商务合同外部项目名称id'")
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    @Column(name = "innerNameId",columnDefinition = "VARCHAR(255)   COMMENT '市场信息记录内部项目名称id'")
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    @Column(name = "saleNumId",  columnDefinition = "VARCHAR(255)   COMMENT '商务项目合同基本信息对应销售合同号id'")
    private String saleNumId;




    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getSignCondition() {
        return signCondition;
    }

    public void setSignCondition(String signCondition) {
        this.signCondition = signCondition;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getDepatchNum() {
        return depatchNum;
    }

    public void setDepatchNum(String depatchNum) {
        this.depatchNum = depatchNum;
    }

    public LocalDate getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(LocalDate startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public LocalDate getPlanCompleteTime() {
        return planCompleteTime;
    }

    public void setPlanCompleteTime(LocalDate planCompleteTime) {
        this.planCompleteTime = planCompleteTime;
    }

    public void setCompleteTime(LocalDate completeTime) {
        this.completeTime = completeTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCharger() {
        return customerCharger;
    }

    public void setCustomerCharger(String customerCharger) {
        this.customerCharger = customerCharger;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getEnginSupervision() {
        return enginSupervision;
    }

    public void setEnginSupervision(String enginSupervision) {
        this.enginSupervision = enginSupervision;
    }

    public String getEnginPlace() {
        return enginPlace;
    }

    public void setEnginPlace(String enginPlace) {
        this.enginPlace = enginPlace;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getCompleteTime() {
        return completeTime;
    }

    public String getCompleteCondition() {
        return completeCondition;
    }

    public void setCompleteCondition(String completeCondition) {
        this.completeCondition = completeCondition;
    }

    public String getCompleteReport() {
        return completeReport;
    }

    public void setCompleteReport(String completeReport) {
        this.completeReport = completeReport;
    }

    public String getContractFollow() {
        return contractFollow;
    }

    public void setContractFollow(String contractFollow) {
        this.contractFollow = contractFollow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOuterNameId() {
        return outerNameId;
    }

    public void setOuterNameId(String outerNameId) {
        this.outerNameId = outerNameId;
    }

    public String getInnerNameId() {
        return innerNameId;
    }

    public void setInnerNameId(String innerNameId) {
        this.innerNameId = innerNameId;
    }

    public String getSaleNumId() {
        return saleNumId;
    }

    public void setSaleNumId(String saleNumId) {
        this.saleNumId = saleNumId;
    }

}