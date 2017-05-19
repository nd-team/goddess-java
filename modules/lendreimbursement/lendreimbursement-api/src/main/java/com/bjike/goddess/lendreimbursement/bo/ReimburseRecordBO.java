package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;

import javax.persistence.Column;

/**
 * 报销记录业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseRecordBO extends BaseBO {

    /**
     * 报销发生日期
     */
    private String occureDate;

    /**
     * 填单人
     */
    private String filler;

    /**
     * 提交时间
     */
    private String commitDate;

    /**
     * 处理时间
     */
    private String editDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 当天任务
     */
    private String dayTask;

    /**
     * 补充内容
     */
    private String addContent;

    /**
     * 报销单号
     */
    private String reimNumber;

    /**
     * 报销人
     */
    private String reimer;

    /**
     * 项目
     */
    private String project;

    /**
     * 单据编号
     */
    private String ticketNumber;

    /**
     * 单据数量
     */
    private Double ticketQuantity;

    /**
     * 报销总金额
     */
    private Double reimMoney;

    /**
     * 报销人备注
     */
    private String reimerRemark;

    /**
     * 是否有发票
     */
    private String ticketCondition;

    /**
     * 无发票情况备注
     */
    private String noTicketRemark;

    /**
     * 收票人
     */
    private String receiveTicketer;

    /**
     * 收票时间
     */
    private String receiveTicketTime;

    /**
     * 收到发票情况
     */
    private String receiveTicketCon;
    /**
     * 是否已收到单据(是/否)
     */
    private String receiveTicketCheck;
    /**
     * 预计付款时间
     */
    private String budgetPayTime;

    /**
     * 负责人
     */
    private String charger;

    /**
     * 负责人审核状态
     */
    private String chargerAuditStatus;

    /**
     * 审核时间
     */
    private String chargerAuditTime;

    /**
     * 审核意见
     */
    private String auditAdvice;

    /**
     * 是否支付
     */
    private String payCondition;

    /**
     * 参与人
     */
    private String attender;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 说明
     */
    private String plainInfo;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 支付计划
     */
    private String payPlan;

    /**
     * 支付时间
     */
    private String payTime;

    /**
     * 状态
     */
    private ReimStatus reimStatus;

    /**
     * 是否已生成记账凭证
     */
    private String AccountFlag;

    /**
     * 付款来源
     */
    private String payOrigin;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getOccureDate() {
        return occureDate;
    }

    public void setOccureDate(String occureDate) {
        this.occureDate = occureDate;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
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

    public String getReceiveTicketCheck() {
        return receiveTicketCheck;
    }

    public void setReceiveTicketCheck(String receiveTicketCheck) {
        this.receiveTicketCheck = receiveTicketCheck;
    }

    public String getReceiveTicketTime() {
        return receiveTicketTime;
    }

    public void setReceiveTicketTime(String receiveTicketTime) {
        this.receiveTicketTime = receiveTicketTime;
    }

    public String getReceiveTicketCon() {
        return receiveTicketCon;
    }

    public void setReceiveTicketCon(String receiveTicketCon) {
        this.receiveTicketCon = receiveTicketCon;
    }

    public String getBudgetPayTime() {
        return budgetPayTime;
    }

    public void setBudgetPayTime(String budgetPayTime) {
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

    public String getChargerAuditTime() {
        return chargerAuditTime;
    }

    public void setChargerAuditTime(String chargerAuditTime) {
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
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

    public void setAccountFlag(String AccountFlag) {
        this.AccountFlag = AccountFlag;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}