package com.bjike.goddess.lendreimbursement.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseRecordExcel extends BaseTO {

    /**
     * 报销发生日期(年月日)
     */
    @ExcelHeader(name = "报销发生日期(年月日)", notNull = true)
    private String occureDate;

    /**
     * 填单人
     */
    @ExcelHeader(name = "填单人")
    private String filler;

    /**
     * 提交时间
     */
    @ExcelHeader(name = "提交时间")
    private String commitDate;

    /**
     * 处理时间
     */
    @ExcelHeader(name = "处理时间")
    private String editDate;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectGroup;

    /**
     * 当天任务
     */
    @ExcelHeader(name = "当天任务")
    private String dayTask;

    /**
     * 补充内容
     */
    @ExcelHeader(name = "补充内容", notNull = true)
    private String addContent;

    /**
     * 报销单号
     */
    @ExcelHeader(name = "报销单号")
    private String reimNumber;

    /**
     * 报销人
     */
    @ExcelHeader(name = "报销人", notNull = true)
    private String reimer;

    /**
     * 项目
     */
    @ExcelHeader(name = "项目", notNull = true)
    private String project;

    /**
     * 单据编号
     */
    @ExcelHeader(name = "单据编号")
    private String ticketNumber;

    /**
     * 单据数量
     */

    @ExcelHeader(name = "单据数量")
    private Double ticketQuantity;

    /**
     * 报销总金额
     */
    @ExcelHeader(name = "报销总金额", notNull = true)
    private Double reimMoney;

    /**
     * 报销人备注
     */
    @ExcelHeader(name = "报销人备注")
    private String reimerRemark;

    /**
     * 是否有发票(是/否)
     */
    @ExcelHeader(name = "是否有发票(是/否)")
    private String ticketCondition;

    /**
     * 无发票情况备注
     */
    @ExcelHeader(name = "无发票情况备注")
    private String noTicketRemark;

    /**
     * 收票人
     */
    @ExcelHeader(name = "收票人")
    private String receiveTicketer;

    /**
     * 收票时间
     */
    @ExcelHeader(name = "收票时间")
    private String receiveTicketTime;

    /**
     * 收到发票情况
     */
    @ExcelHeader(name = "提交时间")
    private String receiveTicketCon;

    /**
     * 是否已收到单据(是/否)
     */
    @ExcelHeader(name = "是否已收到单据(是/否)")
    private String receiveTicketCheck;

    /**
     * 预计付款时间
     */
    @ExcelHeader(name = "预计付款时间")
    private String budgetPayTime;

    /**
     * 负责人
     */
    @ExcelHeader(name = "负责人", notNull = true)
    private String charger;

    /**
     * 审核情况(通过/不通过)
     */
    @ExcelHeader(name = "审核情况(通过/不通过)")
    private String chargerAuditStatus;
    /**
     * 是否确认冻结(是/否)
     */
    @ExcelHeader(name = "是否确认冻结(是/否)")
    private String sureCongel;

    /**
     * 审核时间
     */
    @ExcelHeader(name = "审核时间")
    private String chargerAuditTime;

    /**
     * 审核意见
     */
    @ExcelHeader(name = "审核意见")
    private String auditAdvice;

    /**
     * 是否支付(是/否)
     */
    @ExcelHeader(name = "是否支付(是/否)")
    private String payCondition;

    /**
     * 参与人
     */
    @ExcelHeader(name = "参与人")
    private String attender;

    /**
     * 一级科目
     */
    @ExcelHeader(name = "一级科目", notNull = true)
    private String firstSubject;

    /**
     * 二级科目
     */
    @ExcelHeader(name = "二级科目", notNull = true)
    private String secondSubject;

    /**
     * 三级科目
     */
    @ExcelHeader(name = "三级科目", notNull = true)
    private String thirdSubject;

    /**
     * 说明
     */
    @ExcelHeader(name = "说明", notNull = true)
    private String plainInfo;

    /**
     * 摘要
     */
    @ExcelHeader(name = "摘要", notNull = true)
    private String summary;

    /**
     * 支付计划
     */
    @ExcelHeader(name = "支付计划")
    private String payPlan;

    /**
     * 支付时间
     */
    @ExcelHeader(name = "支付时间")
    private String payTime;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态")
    private String reimStatus;

    /**
     * 是否已生成记账凭证(是/否)
     */
    @ExcelHeader(name = "是否已生成记账凭证(是/否)")
    private String accountFlag;

    /**
     * 付款来源
     */
    @ExcelHeader(name = "付款来源")
    private String payOrigin;


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

    public String getSureCongel() {
        return sureCongel;
    }

    public void setSureCongel(String sureCongel) {
        this.sureCongel = sureCongel;
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

    public String getReimStatus() {
        return reimStatus;
    }

    public void setReimStatus(String reimStatus) {
        this.reimStatus = reimStatus;
    }

    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }


}