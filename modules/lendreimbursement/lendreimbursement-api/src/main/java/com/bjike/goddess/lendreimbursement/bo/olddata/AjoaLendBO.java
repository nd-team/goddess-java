package com.bjike.goddess.lendreimbursement.bo.olddata;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 老系统的借款业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaLendBO extends BaseBO {

    /**
     * 审核人员职位名称
     */
    private String writer;

    /**
     * 借款日期
     */
    private String lendDate;

    /**
     * 预计借款日期
     */
    private String planDate;

    /**
     * 借款人
     */
    private String borrower;

    /**
     * 参与人
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 借款原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 借款方式
     */
    private String lendWay;

    /**
     * 金额
     */
    private String money;

    /**
     * 商品地址
     */
    private String url;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款日期
     */
    private String payDate;

    /**
     * 支付来源
     */
    private String payment;

    /**
     * 单据数量
     */
    private Double billNum;

    /**
     * 实际金额
     */
    private Double realMoney;

    /**
     * 报销金额
     */
    private Double payMoney;

    /**
     * 退回金额
     */
    private Double quitMoney;

    /**
     * 填写人
     */
    private String repayer;

    /**
     * 填写日期
     */
    private String repayDate;

    /**
     * 归还日期
     */
    private String quitDate;

    /**
     * 还款方式
     */
    private String repayWay;

    /**
     * 银行账号
     */
    private String bank;

    /**
     * 修改内容
     */
    private String matter;

    /**
     * 负责人
     */
    private String auditor;

    /**
     * 负责人审核意见
     */
    private String manageIdea;

    /**
     * 财务审核人
     */
    private String finance;

    /**
     * 财务意见
     */
    private String financeIdea;

    /**
     * 总经办
     */
    private String leader;

    /**
     * 总经办意见
     */
    private String leadIdea;

    /**
     * 代理审核备注
     */
    private String content;

    /**
     * 总经办审核时间
     */
    private String manageDate;

    /**
     * 财务审核时间
     */
    private String financeDate;

    /**
     * 不知道
     */
    private String leadDate;

    /**
     * 不知道
     */
    private int manageState;

    /**
     * 不知道
     */
    private int fundState;

    /**
     * 不知道
     */
    private int budgetState;

    /**
     * 不知道
     */
    private int leadState;

    /**
     * 不知道
     */
    private int pastState;

    /**
     * 状态
     */
    private int payState;

    /**
     * 寄件人
     */
    private String sender;

    /**
     * 寄件日期
     */
    private String sendDate;

    /**
     * 寄件情况
     */
    private String sendCase;

    /**
     * 地区
     */
    private String address;

    /**
     * 收件人
     */
    private String biller;

    /**
     * 收件日期
     */
    private String billDate;

    /**
     * 收票情况
     */
    private String billCase;

    /**
     * 科目id
     */
    private String sub_id;

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

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLendWay() {
        return lendWay;
    }

    public void setLendWay(String lendWay) {
        this.lendWay = lendWay;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Double getBillNum() {
        return billNum;
    }

    public void setBillNum(Double billNum) {
        this.billNum = billNum;
    }

    public Double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Double realMoney) {
        this.realMoney = realMoney;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Double getQuitMoney() {
        return quitMoney;
    }

    public void setQuitMoney(Double quitMoney) {
        this.quitMoney = quitMoney;
    }

    public String getRepayer() {
        return repayer;
    }

    public void setRepayer(String repayer) {
        this.repayer = repayer;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getQuitDate() {
        return quitDate;
    }

    public void setQuitDate(String quitDate) {
        this.quitDate = quitDate;
    }

    public String getRepayWay() {
        return repayWay;
    }

    public void setRepayWay(String repayWay) {
        this.repayWay = repayWay;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getManageIdea() {
        return manageIdea;
    }

    public void setManageIdea(String manageIdea) {
        this.manageIdea = manageIdea;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getFinanceIdea() {
        return financeIdea;
    }

    public void setFinanceIdea(String financeIdea) {
        this.financeIdea = financeIdea;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeadIdea() {
        return leadIdea;
    }

    public void setLeadIdea(String leadIdea) {
        this.leadIdea = leadIdea;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getManageDate() {
        return manageDate;
    }

    public void setManageDate(String manageDate) {
        this.manageDate = manageDate;
    }

    public String getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(String financeDate) {
        this.financeDate = financeDate;
    }

    public String getLeadDate() {
        return leadDate;
    }

    public void setLeadDate(String leadDate) {
        this.leadDate = leadDate;
    }

    public int getManageState() {
        return manageState;
    }

    public void setManageState(int manageState) {
        this.manageState = manageState;
    }

    public int getFundState() {
        return fundState;
    }

    public void setFundState(int fundState) {
        this.fundState = fundState;
    }

    public int getBudgetState() {
        return budgetState;
    }

    public void setBudgetState(int budgetState) {
        this.budgetState = budgetState;
    }

    public int getLeadState() {
        return leadState;
    }

    public void setLeadState(int leadState) {
        this.leadState = leadState;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendCase() {
        return sendCase;
    }

    public void setSendCase(String sendCase) {
        this.sendCase = sendCase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillCase() {
        return billCase;
    }

    public void setBillCase(String billCase) {
        this.billCase = billCase;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}