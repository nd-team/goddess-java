package com.bjike.goddess.checkfunds.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 审批不通过记录业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NotPassAuditBO extends BaseBO {

    /**
     * 核对状态
     */
    private String auditStatus;

    /**
     * 用户名
     */
    private String name;

    /**
     * 银行账号
     */
    private String account;

    /**
     * 对账年份
     */
    private Integer year;

    /**
     * 对账月份
     */
    private Integer month;

    /**
     * 资金流水借方金额
     */
    private Double fundDebtor;

    /**
     * 资金流水贷方金额
     */
    private Double fundcreditor;

    /**
     * 资金流水余额
     */
    private Double fundBalance;

    /**
     * 银行流水借方金额
     */
    private Double bankDebtor;

    /**
     * 银行流水贷方金额
     */
    private Double bankcreditor;

    /**
     * 银行流水余额
     */
    private Double bankBalance;

    /**
     * 借方差额
     */
    private Double debtorDiffer;

    /**
     * 贷方差额
     */
    private Double creditorDiffer;

    /**
     * 余额差额
     */
    private Double balanceDiffer;

    /**
     * 余额核对状态
     */
    private String remainAduitStatus;

    /**
     * 余额调节后
     */
    private String afterReconciliation;

    /**
     * 经办时间
     */
    private String handleTime;

    /**
     * 经办人
     */
    private String handler;

    /**
     * 审批时间
     */
    private String examineTime;

    /**
     * 审批人
     */
    private String examine;

    /**
     * 银企对账id
     */
    private String bankReconciliationId;

    public String getBankReconciliationId() {
        return bankReconciliationId;
    }

    public void setBankReconciliationId(String bankReconciliationId) {
        this.bankReconciliationId = bankReconciliationId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getFundDebtor() {
        return fundDebtor;
    }

    public void setFundDebtor(Double fundDebtor) {
        this.fundDebtor = fundDebtor;
    }

    public Double getFundcreditor() {
        return fundcreditor;
    }

    public void setFundcreditor(Double fundcreditor) {
        this.fundcreditor = fundcreditor;
    }

    public Double getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(Double fundBalance) {
        this.fundBalance = fundBalance;
    }

    public Double getBankDebtor() {
        return bankDebtor;
    }

    public void setBankDebtor(Double bankDebtor) {
        this.bankDebtor = bankDebtor;
    }

    public Double getBankcreditor() {
        return bankcreditor;
    }

    public void setBankcreditor(Double bankcreditor) {
        this.bankcreditor = bankcreditor;
    }

    public Double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Double getDebtorDiffer() {
        return debtorDiffer;
    }

    public void setDebtorDiffer(Double debtorDiffer) {
        this.debtorDiffer = debtorDiffer;
    }

    public Double getCreditorDiffer() {
        return creditorDiffer;
    }

    public void setCreditorDiffer(Double creditorDiffer) {
        this.creditorDiffer = creditorDiffer;
    }

    public Double getBalanceDiffer() {
        return balanceDiffer;
    }

    public void setBalanceDiffer(Double balanceDiffer) {
        this.balanceDiffer = balanceDiffer;
    }

    public String getRemainAduitStatus() {
        return remainAduitStatus;
    }

    public void setRemainAduitStatus(String remainAduitStatus) {
        this.remainAduitStatus = remainAduitStatus;
    }

    public String getAfterReconciliation() {
        return afterReconciliation;
    }

    public void setAfterReconciliation(String afterReconciliation) {
        this.afterReconciliation = afterReconciliation;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(String examineTime) {
        this.examineTime = examineTime;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }
}

