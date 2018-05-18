package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.moneyside.enums.PassStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金退出申请表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_moneyexitapply")
public class MoneyExitApply extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 协议投资金额
     */
    @Column(name = "amountAagree", columnDefinition = "DECIMAL(10,2)   COMMENT '协议投资金额'")
    private Double amountAagree;

    /**
     * 累计投资金额
     */
    @Column(name = "accumulativeInvestMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资金额'")
    private Double accumulativeInvestMoney;

    /**
     * 投资方式
     */
    @Column(name = "investForm", columnDefinition = "VARCHAR(255)   COMMENT '投资方式'")
    private String investForm;

    /**
     * 投资项目
     */
    @Column(name = "investProject", columnDefinition = "VARCHAR(255)   COMMENT '投资项目'")
    private String investProject;

    /**
     * 开工时间
     */
    @Column(name = "startProjectTime", columnDefinition = "DATE   COMMENT '开工时间'")
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @Column(name = "endProjectTime", columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate endProjectTime;

    /**
     * 项目进度
     */
    @Column(name = "projectProgress", columnDefinition = "VARCHAR(255)   COMMENT '项目进度'")
    private String projectProgress;

    /**
     * 结算进度
     */
    @Column(name = "settlementProgress", columnDefinition = "VARCHAR(255)   COMMENT '结算进度'")
    private String settlementProgress;

    /**
     * 预计到账时间
     */
    @Column(name = "forecastArriveTime", columnDefinition = "DATE   COMMENT '预计到账时间'")
    private LocalDate forecastArriveTime;

    /**
     * 退出时间
     */
    @Column(name = "exitTime", columnDefinition = "DATE   COMMENT '退出时间'")
    private LocalDate exitTime;

    /**
     * 退出利率
     */
    @Column(name = "exitInterestRate",  columnDefinition = "DECIMAL(10,2)   COMMENT '退出利率'")
    private Double exitInterestRate;

    /**
     * 退出金额
     */
    @Column(name = "exitMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '退出金额'")
    private Double exitMoney;

    /**
     * 剩余金额（累计投资金额-退出金额）
     */
    @Column(name = "residueMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '剩余金额（累计投资金额-退出金额）'")
    private Double residueMoney;

    /**
     * 退出利息
     */
    @Column(name = "exitInterest",  columnDefinition = "DECIMAL(10,2)   COMMENT '退出利息'")
    private Double exitInterest;
    /**
     * 是否通过
     */
    @Column(name = "pass", columnDefinition = "TINYINT(2)   COMMENT '是否通过'")
    private PassStatus pass;

    /**
     * 审批人
     */
    @Column(name = "approver",columnDefinition = "VARCHAR(255)   COMMENT '审批人'")
    private String approver;

    /**
     * 审批时间
     */
    @Column(name = "approverTime",  columnDefinition = "DATE   COMMENT '审批时间'")
    private LocalDate approverTime;

    /**
     * 审批意见
     */
    @Column(name = "approverOpinion", columnDefinition = "VARCHAR(255)   COMMENT '审批意见'")
    private String approverOpinion;


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

    public LocalDate getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(LocalDate startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public LocalDate getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(LocalDate endProjectTime) {
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

    public LocalDate getForecastArriveTime() {
        return forecastArriveTime;
    }

    public void setForecastArriveTime(LocalDate forecastArriveTime) {
        this.forecastArriveTime = forecastArriveTime;
    }

    public LocalDate getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDate exitTime) {
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

    public LocalDate getApproverTime() {
        return approverTime;
    }

    public void setApproverTime(LocalDate approverTime) {
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