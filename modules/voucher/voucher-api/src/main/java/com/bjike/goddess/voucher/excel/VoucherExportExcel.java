package com.bjike.goddess.voucher.excel;

import com.bjike.goddess.voucher.enums.AuditStatus;
import com.bjike.goddess.voucher.enums.CheckStatus;
import com.bjike.goddess.voucher.enums.TransferStatus;

import java.io.Serializable;

/**
 * 记账凭证生成表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VoucherExportExcel implements Serializable{

    /**
     * 凭证字
     */
    private String voucherWord;

    /**
     * 凭证字号
     */
    private Double voucherNum;

    /**
     * 凭证日期
     */
    private String voucherDate;

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
     * 借方金额
     */
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    private Double loanMoney;

    /**
     * 摘要
     */
    private String sumary;

    /**
     * 来源
     */
    private String source;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 制单人
     */
    private String ticketer;

    /**
     * 票据数量
     */
    private Double ticketNum;

    /**
     * 附件
     */
    private String extraFile;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核状态
     */
    private AuditStatus auditStatus;

    /**
     * 过帐状态
     */
    private TransferStatus transferStatus;

    /**
     * 结帐状态
     */
    private CheckStatus checkStatus;

    /**
     * 借贷金额合计
     */
    private Double moneyTotal;

    /**
     * 合计id
     */
    private String totalId;


    public String getVoucherWord() {
        return voucherWord;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

    public Double getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(Double voucherNum) {
        this.voucherNum = voucherNum;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
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

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getTicketer() {
        return ticketer;
    }

    public void setTicketer(String ticketer) {
        this.ticketer = ticketer;
    }

    public Double getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Double ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getExtraFile() {
        return extraFile;
    }

    public void setExtraFile(String extraFile) {
        this.extraFile = extraFile;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Double getMoneyTotal() {
        return moneyTotal;
    }

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public void setMoneyTotal(Double moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}