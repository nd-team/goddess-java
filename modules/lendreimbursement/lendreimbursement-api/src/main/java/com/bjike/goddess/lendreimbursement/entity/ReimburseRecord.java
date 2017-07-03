package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_reimburserecord")
public class ReimburseRecord extends BaseEntity {

    /**
     * 报销发生日期
     */
    @Column(name = "occureDate",  columnDefinition = "DATE   COMMENT '报销发生日期'")
    private LocalDate occureDate;

    /**
     * 填单人
     */
    @Column(name = "filler", columnDefinition = "VARCHAR(255)   COMMENT '填单人'")
    private String filler;

    /**
     * 提交时间
     */
    @Column(name = "commitDate", columnDefinition = "DATE  COMMENT '提交时间'")
    private LocalDate commitDate;

    /**
     * 处理时间
     */
    @Column(name = "editDate",  columnDefinition = "DATE   COMMENT '处理时间'")
    private LocalDate editDate;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 当天任务
     */
    @Column(name = "dayTask",  columnDefinition = "VARCHAR(255)   COMMENT '当天任务'")
    private String dayTask;

    /**
     * 补充内容
     */
    @Column(name = "addContent",  columnDefinition = "VARCHAR(255)   COMMENT '补充内容'")
    private String addContent;

    /**
     * 报销单号
     */
    @Column(name = "reimNumber", nullable = false ,unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '报销单号'")
    private String reimNumber;

    /**
     * 报销人
     */
    @Column(name = "reimer",  columnDefinition = "VARCHAR(255)   COMMENT '报销人'")
    private String reimer;

    /**
     * 项目
     */
    @Column(name = "project",  columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 单据编号
     */
    @Column(name = "ticketNumber",  nullable = false ,unique = true , columnDefinition = "VARCHAR(255)   COMMENT '单据编号'")
    private String ticketNumber;

    /**
     * 单据数量
     */
    @Column(name = "ticketQuantity",  columnDefinition = "DECIMAL(10,2)   COMMENT '单据数量'")
    private Double ticketQuantity;

    /**
     * 报销总金额
     */
    @Column(name = "reimMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '报销总金额'")
    private Double reimMoney;

    /**
     * 报销人备注
     */
    @Column(name = "reimerRemark",  columnDefinition = "VARCHAR(255)   COMMENT '报销人备注'")
    private String reimerRemark;

    /**
     * 是否有发票(是/否)
     */
    @Column(name = "ticketCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否有发票(是/否)'")
    private String ticketCondition;

    /**
     * 无发票情况备注
     */
    @Column(name = "noTicketRemark",  columnDefinition = "VARCHAR(255)   COMMENT '无发票情况备注'")
    private String noTicketRemark;

    /**
     * 收票人
     */
    @Column(name = "receiveTicketer",  columnDefinition = "VARCHAR(255)   COMMENT '收票人'")
    private String receiveTicketer;

    /**
     * 收票时间
     */
    @Column(name = "receiveTicketTime",  columnDefinition = "DATE  COMMENT '收票时间'")
    private LocalDate receiveTicketTime;

    /**
     * 收到发票情况
     */
    @Column(name = "receiveTicketCon",  columnDefinition = "VARCHAR(255)   COMMENT '收到发票情况'")
    private String receiveTicketCon;

    /**
     * 是否已收到单据(是/否)
     */
    @Column(name = "receiveTicketCheck",  columnDefinition = "VARCHAR(255)   COMMENT '是否已收到单据(是/否)'")
    private String receiveTicketCheck;

    /**
     * 预计付款时间
     */
    @Column(name = "budgetPayTime",  columnDefinition = "DATE   COMMENT '预计付款时间'")
    private LocalDate budgetPayTime;

    /**
     * 负责人
     */
    @Column(name = "charger",  columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String charger;

    /**
     * 负责人审核状态(通过/不通过)
     */
    @Column(name = "chargerAuditStatus",  columnDefinition = "VARCHAR(255)   COMMENT '负责人审核状态(通过/不通过)'")
    private String chargerAuditStatus;

    /**
     * 审核时间
     */
    @Column(name = "chargerAuditTime",  columnDefinition = "DATE  COMMENT '审核时间'")
    private LocalDate chargerAuditTime;

    /**
     * 审核意见
     */
    @Column(name = "auditAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditAdvice;

    /**
     * 是否支付(是/否)
     */
    @Column(name = "payCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否支付(是/否)'")
    private String payCondition;

    /**
     * 参与人
     */
    @Column(name = "attender",  columnDefinition = "VARCHAR(255)   COMMENT '参与人'")
    private String attender;

    /**
     * 一级科目
     */
    @Column(name = "firstSubject",  columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject",  columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject",  columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 说明
     */
    @Column(name = "plainInfo",  columnDefinition = "VARCHAR(255)   COMMENT '说明'")
    private String plainInfo;

    /**
     * 摘要
     */
    @Column(name = "summary",  columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String summary;

    /**
     * 支付计划
     */
    @Column(name = "payPlan",  columnDefinition = "VARCHAR(255)   COMMENT '支付计划'")
    private String payPlan;

    /**
     * 支付时间
     */
    @Column(name = "payTime",  columnDefinition = "DATE  COMMENT '支付时间'")
    private LocalDate payTime;

    /**
     * 状态
     */
    @Column(name = "reimStatus",  columnDefinition = "INT(2)   COMMENT '状态'")
    private ReimStatus reimStatus;

    /**
     * 是否已生成记账凭证(是/否)
     */
    @Column(name = "AccountFlag",  columnDefinition = "VARCHAR(255)   COMMENT '是否已生成记账凭证(是/否)'")
    private String AccountFlag;
    /**
     * 付款来源
     */
    @Column(name = "payOrigin",  columnDefinition = "VARCHAR(255)   COMMENT '付款来源'")
    private String payOrigin;


    public LocalDate getOccureDate() {
        return occureDate;
    }

    public void setOccureDate(LocalDate occureDate) {
        this.occureDate = occureDate;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public LocalDate getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDate commitDate) {
        this.commitDate = commitDate;
    }

    public LocalDate getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDate editDate) {
        this.editDate = editDate;
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

    public String getDayTask() {
        return dayTask;
    }

    public void setDayTask(String dayTask) {
        this.dayTask = dayTask;
    }

    public String getAddContent() {
        return addContent;
    }

    public void setAddContent(String addContent) {
        this.addContent = addContent;
    }

    public String getReimNumber() {
        return reimNumber;
    }

    public void setReimNumber(String reimNumber) {
        this.reimNumber = reimNumber;
    }

    public String getReimer() {
        return reimer;
    }

    public void setReimer(String reimer) {
        this.reimer = reimer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Double getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(Double ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public String getReimerRemark() {
        return reimerRemark;
    }

    public void setReimerRemark(String reimerRemark) {
        this.reimerRemark = reimerRemark;
    }

    public String getTicketCondition() {
        return ticketCondition;
    }

    public void setTicketCondition(String ticketCondition) {
        this.ticketCondition = ticketCondition;
    }

    public String getReceiveTicketCheck() {
        return receiveTicketCheck;
    }

    public void setReceiveTicketCheck(String receiveTicketCheck) {
        this.receiveTicketCheck = receiveTicketCheck;
    }

    public String getNoTicketRemark() {
        return noTicketRemark;
    }

    public void setNoTicketRemark(String noTicketRemark) {
        this.noTicketRemark = noTicketRemark;
    }

    public String getReceiveTicketer() {
        return receiveTicketer;
    }

    public void setReceiveTicketer(String receiveTicketer) {
        this.receiveTicketer = receiveTicketer;
    }

    public LocalDate getReceiveTicketTime() {
        return receiveTicketTime;
    }

    public void setReceiveTicketTime(LocalDate receiveTicketTime) {
        this.receiveTicketTime = receiveTicketTime;
    }

    public String getReceiveTicketCon() {
        return receiveTicketCon;
    }

    public void setReceiveTicketCon(String receiveTicketCon) {
        this.receiveTicketCon = receiveTicketCon;
    }

    public LocalDate getBudgetPayTime() {
        return budgetPayTime;
    }

    public void setBudgetPayTime(LocalDate budgetPayTime) {
        this.budgetPayTime = budgetPayTime;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getChargerAuditStatus() {
        return chargerAuditStatus;
    }

    public void setChargerAuditStatus(String chargerAuditStatus) {
        this.chargerAuditStatus = chargerAuditStatus;
    }

    public LocalDate getChargerAuditTime() {
        return chargerAuditTime;
    }

    public void setChargerAuditTime(LocalDate chargerAuditTime) {
        this.chargerAuditTime = chargerAuditTime;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public String getAttender() {
        return attender;
    }

    public void setAttender(String attender) {
        this.attender = attender;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getPlainInfo() {
        return plainInfo;
    }

    public void setPlainInfo(String plainInfo) {
        this.plainInfo = plainInfo;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPayPlan() {
        return payPlan;
    }

    public void setPayPlan(String payPlan) {
        this.payPlan = payPlan;
    }

    public LocalDate getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDate payTime) {
        this.payTime = payTime;
    }

    public ReimStatus getReimStatus() {
        return reimStatus;
    }

    public void setReimStatus(ReimStatus reimStatus) {
        this.reimStatus = reimStatus;
    }

    public String getAccountFlag() {
        return AccountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        AccountFlag = accountFlag;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }
}