package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 项目实施审核(针对没派工单情况)
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_projectcarryaudit")
public class ProjectCarryAudit extends BaseEntity {

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
     * 销售合同号
     */
    @Column(name = "saleNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String saleNum;

    /**
     * 立项情况
     */
    @Column(name = "signProjectCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '立项情况'")
    private String signProjectCondition;

    /**
     * 工作内容
     */
    @Column(name = "workContent", columnDefinition = "VARCHAR(255)   COMMENT '工作内容'")
    private String workContent;

    /**
     * 规划模块审核意见
     */
    @Column(name = "planModuleAudit", columnDefinition = "VARCHAR(255)   COMMENT '规划模块审核意见'")
    private String planModuleAudit;

    /**
     * 预算模块审核意见
     */
    @Column(name = "budgetModuleAudit", columnDefinition = "VARCHAR(255)   COMMENT '预算模块审核意见'")
    private String budgetModuleAudit;

    /**
     * 商务决策层审核意见
     */
    @Column(name = "businessModuleAudit", columnDefinition = "VARCHAR(255)   COMMENT '商务决策层审核意见'")
    private String businessModuleAudit;

    /**
     * 总经办审核意见
     */
    @Column(name = "manageModuleAudit", columnDefinition = "VARCHAR(255)   COMMENT '总经办审核意见'")
    private String manageModuleAudit;

    /**
     * 是否实施
     */
    @Column(name = "carryCondition", columnDefinition = "VARCHAR(255)   COMMENT '是否实施'")
    private String carryCondition;

    /**
     * 催派工单进度
     */
    @Column(name = "dispatchProcing", columnDefinition = "VARCHAR(255)   COMMENT '催派工单进度'")
    private String dispatchProcing;

    /**
     * 商务合同外部项目名称id
     */
    @Column(name = "outerNameId", columnDefinition = "VARCHAR(255)   COMMENT '商务合同外部项目名称id'")
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    @Column(name = "innerNameId", columnDefinition = "VARCHAR(255)   COMMENT '市场信息记录内部项目名称id'")
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    @Column(name = "saleNumId", columnDefinition = "VARCHAR(255)   COMMENT '商务项目合同基本信息对应销售合同号id'")
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

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSignProjectCondition() {
        return signProjectCondition;
    }

    public void setSignProjectCondition(String signProjectCondition) {
        this.signProjectCondition = signProjectCondition;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getPlanModuleAudit() {
        return planModuleAudit;
    }

    public void setPlanModuleAudit(String planModuleAudit) {
        this.planModuleAudit = planModuleAudit;
    }

    public String getBudgetModuleAudit() {
        return budgetModuleAudit;
    }

    public void setBudgetModuleAudit(String budgetModuleAudit) {
        this.budgetModuleAudit = budgetModuleAudit;
    }

    public String getBusinessModuleAudit() {
        return businessModuleAudit;
    }

    public void setBusinessModuleAudit(String businessModuleAudit) {
        this.businessModuleAudit = businessModuleAudit;
    }

    public String getManageModuleAudit() {
        return manageModuleAudit;
    }

    public void setManageModuleAudit(String manageModuleAudit) {
        this.manageModuleAudit = manageModuleAudit;
    }

    public String getCarryCondition() {
        return carryCondition;
    }

    public void setCarryCondition(String carryCondition) {
        this.carryCondition = carryCondition;
    }

    public String getDispatchProcing() {
        return dispatchProcing;
    }

    public void setDispatchProcing(String dispatchProcing) {
        this.dispatchProcing = dispatchProcing;
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