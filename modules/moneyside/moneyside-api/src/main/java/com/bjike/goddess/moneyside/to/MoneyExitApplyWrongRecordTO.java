package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.moneyside.enums.PassStatus;

/**
 * 资金退出申请有误记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyExitApplyWrongRecordTO extends BaseTO {

    /**
     * 投资人
     */
    private String investor;

    /**
     * 协议投资金额
     */
    private Double amountAagree;

    /**
     * 累计投资金额
     */
    private Double accumulativeInvestMoney;

    /**
     * 投资方式
     */
    private String investForm;

    /**
     * 投资项目
     */
    private String investProject;

    /**
     * 开工时间
     */
    private String startProjectTime;

    /**
     * 完工时间
     */
    private String endProjectTime;

    /**
     * 项目进度
     */
    private String projectProgress;

    /**
     * 结算进度
     */
    private String settlementProgress;

    /**
     * 预计到账时间
     */
    private String forecastArriveTime;

    /**
     * 退出时间
     */
    private String exitTime;

    /**
     * 退出利率
     */
    private Double exitInterestRate;

    /**
     * 退出金额
     */
    private Double exitMoney;

    /**
     * 剩余金额（累计投资金额-退出金额）
     */
    private Double residueMoney;

    /**
     * 退出利息
     */
    private Double exitInterest;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    private String approverTime;

    /**
     * 审批意见
     */
    private String approverOpinion;

    /**
     * 是否通过
     */
    private PassStatus pass;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Double getAmountAagree() {
        return amountAagree;
    }

    public void setAmountAagree(Double amountAagree) {
        this.amountAagree = amountAagree;
    }

    public Double getAccumulativeInvestMoney() {
        return accumulativeInvestMoney;
    }

    public void setAccumulativeInvestMoney(Double accumulativeInvestMoney) {
        this.accumulativeInvestMoney = accumulativeInvestMoney;
    }

    public String getInvestForm() {
        return investForm;
    }

    public void setInvestForm(String investForm) {
        this.investForm = investForm;
    }

    public String getInvestProject() {
        return investProject;
    }

    public void setInvestProject(String investProject) {
        this.investProject = investProject;
    }

    public String getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(String startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public String getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(String endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }

    public String getForecastArriveTime() {
        return forecastArriveTime;
    }

    public void setForecastArriveTime(String forecastArriveTime) {
        this.forecastArriveTime = forecastArriveTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public Double getExitInterestRate() {
        return exitInterestRate;
    }

    public void setExitInterestRate(Double exitInterestRate) {
        this.exitInterestRate = exitInterestRate;
    }

    public Double getExitMoney() {
        return exitMoney;
    }

    public void setExitMoney(Double exitMoney) {
        this.exitMoney = exitMoney;
    }

    public Double getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(Double residueMoney) {
        this.residueMoney = residueMoney;
    }

    public Double getExitInterest() {
        return exitInterest;
    }

    public void setExitInterest(Double exitInterest) {
        this.exitInterest = exitInterest;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproverTime() {
        return approverTime;
    }

    public void setApproverTime(String approverTime) {
        this.approverTime = approverTime;
    }

    public String getApproverOpinion() {
        return approverOpinion;
    }

    public void setApproverOpinion(String approverOpinion) {
        this.approverOpinion = approverOpinion;
    }

    public PassStatus getPass() {
        return pass;
    }

    public void setPass(PassStatus pass) {
        this.pass = pass;
    }
}