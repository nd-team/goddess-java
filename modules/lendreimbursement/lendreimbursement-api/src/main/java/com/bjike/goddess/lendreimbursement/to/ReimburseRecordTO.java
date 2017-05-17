package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.ReimStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseRecordTO extends BaseTO {
    public interface TestAddAndEdit {
    }

    public interface TestChargeAudit {
    }
    public interface TestChargeCongel {
    }
    public interface TestAnalysis {
    }
    public interface TestRecieveTicketCheck {
    }

    public interface TestPrePay {
    }
    public interface TestPay {
    }

    /**
     * 报销单号数组
     */
    @NotNull(groups = {ReimburseRecordTO.TestPrePay.class}, message = "报销单号数组不能为空")
    private String[] reimNumbers;

    /**
     * 报销发生日期(年月日)
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "报销发生日期不能为空(年月日)")
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
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 当天任务
     */
    private String dayTask;

    /**
     * 补充内容
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "补充内容不能为空")
    private String addContent;

    /**
     * 报销单号
     */
    private String reimNumber;

    /**
     * 报销人
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "报销人不能为空")
    private String reimer;

    /**
     * 项目
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "项目名称不能为空")
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
    @NotNull(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "报销金额不能为空")
    private Double reimMoney;

    /**
     * 报销人备注
     */
    private String reimerRemark;

    /**
     * 是否有发票(是/否)
     */
    @NotBlank(groups = {ReimburseRecordTO.TestChargeAudit.class}, message = "是否有发票不能为空(是/否)")
    private String ticketCondition;

    /**
     * 无发票情况备注
     */
    private String noTicketRemark;

    /**
     * 收票人
     */
    @NotBlank(groups = {ReimburseRecordTO.TestRecieveTicketCheck.class}, message = "收票人不能为空")
    private String receiveTicketer;

    /**
     * 收票时间
     */
    @NotBlank(groups = {ReimburseRecordTO.TestRecieveTicketCheck.class}, message = "收票时间不能为空")
    private String receiveTicketTime;

    /**
     * 收到发票情况
     */
    private String receiveTicketCon;

    /**
     * 是否已收到单据(是/否)
     */
    @NotBlank(groups = {ReimburseRecordTO.TestRecieveTicketCheck.class}, message = "是否已收到单据(是/否)不能为空")
    private String receiveTicketCheck;

    /**
     * 预计付款时间
     */
    @NotBlank(groups = {ReimburseRecordTO.TestPrePay.class}, message = "预计付款时间不能为空")
    private String budgetPayTime;

    /**
     * 负责人
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "负责人不能为空")
    private String charger;

    /**
     * 审核情况(通过/不通过)
     */
    @NotBlank(groups = {ReimburseRecordTO.TestChargeAudit.class , ReimburseRecordTO.TestAnalysis.class}, message = "审核情况不能为空(是/否)")
    private String chargerAuditStatus;
    /**
     * 是否确认冻结(是/否)
     */
    @NotBlank(groups = {ReimburseRecordTO.TestChargeCongel.class}, message = "是否确认冻结不能为空(是/否)")
    private String sureCongel;

    /**
     * 审核时间
     */
    private String chargerAuditTime;

    /**
     * 审核意见
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAnalysis.class}, message = "是否确认冻结不能为空(是/否)")
    private String auditAdvice;

    /**
     * 是否支付(是/否)
     */
    private String payCondition;

    /**
     * 参与人
     */
    private String attender;

    /**
     * 一级科目
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "一级科目不能为空")
    private String firstSubject;

    /**
     * 二级科目
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "二级科目不能为空")
    private String secondSubject;

    /**
     * 三级科目
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "三级科目不能为空")
    private String thirdSubject;

    /**
     * 说明
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "说明不能为空")
    private String plainInfo;

    /**
     * 摘要
     */
    @NotBlank(groups = {ReimburseRecordTO.TestAddAndEdit.class}, message = "摘要不能为空")
    private String summary;

    /**
     * 支付计划
     */
    @NotBlank(groups = {ReimburseRecordTO.TestPrePay.class}, message = "支付计划不能为空")
    private String payPlan;

    /**
     * 支付时间
     */
    @NotBlank(groups = {ReimburseRecordTO.TestPay.class}, message = "支付时间不能为空")
    private String payTime;

    /**
     * 状态
     */
    private ReimStatus reimStatus;

    /**
     * 是否已生成记账凭证(是/否)
     */
    private String accountFlag;

    /**
     * 付款来源
     */
    @NotBlank(groups = {ReimburseRecordTO.TestPay.class}, message = "付款来源不能为空")
    private String payOrigin;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String[] getReimNumbers() {
        return reimNumbers;
    }

    public void setReimNumbers(String[] reimNumbers) {
        this.reimNumbers = reimNumbers;
    }

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

    public ReimStatus getReimStatus() {
        return reimStatus;
    }

    public void setReimStatus(ReimStatus reimStatus) {
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