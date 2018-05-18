package com.bjike.goddess.lendreimbursement.bo.olddata;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 老系统的报销业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 01:55 ]
 * @Description: [ 老系统的报销业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaReimBO extends BaseBO {

    /**
     * 填单人,系统上是当前登陆用户
     */
    private String writer;

    /**
     * 流水报销单号
     */
    private String runNum;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 报销人地区
     */
    private String area;

    /**
     * 报销人姓名
     */
    private String reimburseName;

    /**
     * 报销提交时间
     */
    private String reportDate;

    /**
     * 预计处理时间,每周二9:30
     */
    private String ruleDate;

    /**
     * 单据编号(报销单号-单据数量排序)
     */
    private String oddNumber;

    /**
     * 单据数量
     */
    private int billNumber;

    /**
     * 报销总金额
     */
    private Double total;

    /**
     * 报销人备注
     */
    private String remark;

    /**
     * 外键关联科目表
     */
    private String sub;

    /**
     * 类别
     */
    private String sort;

    /**
     * 分类
     */
    private String classify;

    /**
     * 明细
     */
    private String detail;

    /**
     * 参与人
     */
    private Double participant;

    /**
     * 收票人
     */
    private Double biller;

    /**
     * 收票时间
     */
    private String billDate;

    /**
     * 收到发票情况
     */
    private Double billCase;

    /**
     * 预计付款时间
     */
    private String planTime;

    /**
     * 不知道
     */
    private String payPlan;

    /**
     * 负责人审核的三种状态
     */
    private int auditorState;

    /**
     * 负责人审核时间
     */
    private String auditorTime;

    /**
     * 负责人审核意见
     */
    private String auditorIdea;

    /**
     * 是否通过所有人的审核
     */
    private int pastState;

    /**
     * 是否支付
     */
    private int payState;

    /**
     * 资金来源
     */
    private String source;

    /**
     * 付款方公司
     */
    private String payment;

    /**
     * 负责人审核,负责人姓名
     */
    private String auditor;

    /**
     * 不知道
     */
    private String payTime;

    /**
     * 总经办确定冻结
     */
    private int confirmCongeal;

    /**
     * 报销发生日期
     */
    private String occurrenceDate;

    /**
     * 不知道
     */
    private String thatDayTask;

    /**
     * 不知道
     */
    private String supplement;

    /**
     * 状态
     */
    private int status;


    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReimburseName() {
        return reimburseName;
    }

    public void setReimburseName(String reimburseName) {
        this.reimburseName = reimburseName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getRuleDate() {
        return ruleDate;
    }

    public void setRuleDate(String ruleDate) {
        this.ruleDate = ruleDate;
    }

    public String getOddNumber() {
        return oddNumber;
    }

    public void setOddNumber(String oddNumber) {
        this.oddNumber = oddNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getParticipant() {
        return participant;
    }

    public void setParticipant(Double participant) {
        this.participant = participant;
    }

    public Double getBiller() {
        return biller;
    }

    public void setBiller(Double biller) {
        this.biller = biller;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public Double getBillCase() {
        return billCase;
    }

    public void setBillCase(Double billCase) {
        this.billCase = billCase;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getPayPlan() {
        return payPlan;
    }

    public void setPayPlan(String payPlan) {
        this.payPlan = payPlan;
    }

    public int getAuditorState() {
        return auditorState;
    }

    public void setAuditorState(int auditorState) {
        this.auditorState = auditorState;
    }

    public String getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(String auditorTime) {
        this.auditorTime = auditorTime;
    }

    public String getAuditorIdea() {
        return auditorIdea;
    }

    public void setAuditorIdea(String auditorIdea) {
        this.auditorIdea = auditorIdea;
    }

    public int getPastState() {
        return pastState;
    }

    public void setPastState(int pastState) {
        this.pastState = pastState;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getConfirmCongeal() {
        return confirmCongeal;
    }

    public void setConfirmCongeal(int confirmCongeal) {
        this.confirmCongeal = confirmCongeal;
    }

    public String getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(String occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public String getThatDayTask() {
        return thatDayTask;
    }

    public void setThatDayTask(String thatDayTask) {
        this.thatDayTask = thatDayTask;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}