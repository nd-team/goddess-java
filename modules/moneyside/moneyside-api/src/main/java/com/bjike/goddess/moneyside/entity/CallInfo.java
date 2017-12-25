package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 招投信息列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_callinfo")
public class CallInfo extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目内部名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '项目内部名称'")
    private String innerProject;

    /**
     * 开工时间
     */
    @Column(name = "startProjectTime",  columnDefinition = "DATE   COMMENT '开工时间'")
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @Column(name = "endProjectTime", columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate endProjectTime;

    /**
     * 合同金额
     */
    @Column(name = "money",  columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double money;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge",  columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 合同单价
     */
    @Column(name = "price", columnDefinition = "DECIMAL(10,2)   COMMENT '合同单价'")
    private Double price;

    /**
     * 合同规模
     */
    @Column(name = "size",  columnDefinition = "VARCHAR(255)   COMMENT '合同规模'")
    private String size;

    /**
     * 是否签订合同
     */
    @Column(name = "is_signedContract", columnDefinition = "TINYINT(2)  COMMENT '是否签订合同'")
    private Boolean signedContract;

    /**
     * 回款周期
     */
    @Column(name = "paybackPeriod", columnDefinition = "VARCHAR(255)   COMMENT '回款周期'")
    private String paybackPeriod;

    /**
     * 预估到账时间
     */
    @Column(name = "forecastArriveTime",columnDefinition = "DATE   COMMENT '预估到账时间'")
    private LocalDate forecastArriveTime;

    /**
     * 承包商
     */
    @Column(name = "contractor", columnDefinition = "VARCHAR(255)   COMMENT '承包商'")
    private String contractor;

    /**
     * 难易程度
     */
    @Column(name = "complexity", columnDefinition = "VARCHAR(255)   COMMENT '难易程度'")
    private String complexity;

    /**
     * 预估利润率
     */
    @Column(name = "forecastProfitMargin", columnDefinition = "DECIMAL(10,2)   COMMENT '预估利润率'")
    private Double forecastProfitMargin;

    /**
     * 资金方分配率
     */
    @Column(name = "capitalAlloRate", columnDefinition = "DECIMAL(10,2)   COMMENT '资金方分配率'")
    private Double capitalAlloRate;

    /**
     * 预估投资收益率
     */
    @Column(name = "forecastReturnInvestment", columnDefinition = "DECIMAL(10,2)   COMMENT '预估投资收益率'")
    private Double forecastReturnInvestment;

    /**
     * 预估分配时间
     */
    @Column(name = "forecastAllotTime",  columnDefinition = "DATE   COMMENT '预估分配时间'")
    private LocalDate forecastAllotTime;

    /**
     * 提取风险准备金率
     */
    @Column(name = "extractRiskRserveRatio",  columnDefinition = "DECIMAL(10,2)   COMMENT '提取风险准备金率'")
    private Double extractRiskRserveRatio;

    /**
     * 筹资总额
     */
    @Column(name = "totalFund", columnDefinition = "DECIMAL(10,2)   COMMENT '筹资总额'")
    private Double totalFund;

    /**
     * 已筹资金额
     */
    @Column(name = "hasBeenRaised", columnDefinition = "DECIMAL(10,2)   COMMENT '已筹资金额'")
    private Double hasBeenRaised;

    /**
     * 还需筹资总额(筹资总额-已筹资金额)
     */
    @Column(name = "needTotalFund", columnDefinition = "DECIMAL(10,2)   COMMENT '还需筹资总额(筹资总额-已筹资金额)'")
    private Double needTotalFund;
    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 投资对象
     */
    @Column(name = "investObject", columnDefinition = "VARCHAR(255)   COMMENT '投资对象'")
    private String investObject;

    /**
     * 投资形式
     */
    @Column(name = "investForm", columnDefinition = "VARCHAR(255)   COMMENT '投资形式'")
    private String investForm;

    /**
     * 投资总额
     */
    @Column(name = "investTotal", columnDefinition = "DECIMAL(10,2)   COMMENT '投资总额'")
    private Double investTotal;

    /**
     * 投资占比（投资总额/筹资总额）
     */
    @Column(name = "investProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '投资占比（投资总额/筹资总额）'")
    private Double investProportion;

    /**
     * 投资日期
     */
    @Column(name = "investDate", columnDefinition = "DATE   COMMENT '投资日期'")
    private LocalDate investDate;

    /**
     * 本次投资额
     */
    @Column(name = "thisInvestMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '本次投资额'")
    private Double thisInvestMoney;

    /**
     * 累计投资额
     */
    @Column(name = "accumulativeInvestMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资额'")
    private Double accumulativeInvestMoney;

    /**
     * 累计投资比（累计投资额/投资总额）
     */
    @Column(name = "accumulativeInvestProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资比（累计投资额/投资总额）'")
    private Double accumulativeInvestProportion;

    /**
     * 提取风险控制保证金（本次投资额/提取风险准备金率）
     */
    @Column(name = "extractRiskControlMargin", columnDefinition = "DECIMAL(10,2)   COMMENT '提取风险控制保证金（本次投资额/提取风险准备金率）'")
    private Double extractRiskControlMargin;

    /**
     * 打款账户
     */
    @Column(name = "moneyAccountName", columnDefinition = "VARCHAR(255)   COMMENT '打款账户'")
    private String moneyAccountName;

    /**
     * 打款账号
     */
    @Column(name = "moneyAccount", columnDefinition = "VARCHAR(255)   COMMENT '打款账号'")
    private String moneyAccount;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getSignedContract() {
        return signedContract;
    }

    public void setSignedContract(Boolean signedContract) {
        this.signedContract = signedContract;
    }

    public String getPaybackPeriod() {
        return paybackPeriod;
    }

    public void setPaybackPeriod(String paybackPeriod) {
        this.paybackPeriod = paybackPeriod;
    }

    public LocalDate getForecastArriveTime() {
        return forecastArriveTime;
    }

    public void setForecastArriveTime(LocalDate forecastArriveTime) {
        this.forecastArriveTime = forecastArriveTime;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public Double getForecastProfitMargin() {
        return forecastProfitMargin;
    }

    public void setForecastProfitMargin(Double forecastProfitMargin) {
        this.forecastProfitMargin = forecastProfitMargin;
    }

    public Double getCapitalAlloRate() {
        return capitalAlloRate;
    }

    public void setCapitalAlloRate(Double capitalAlloRate) {
        this.capitalAlloRate = capitalAlloRate;
    }

    public Double getForecastReturnInvestment() {
        return forecastReturnInvestment;
    }

    public void setForecastReturnInvestment(Double forecastReturnInvestment) {
        this.forecastReturnInvestment = forecastReturnInvestment;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInvestObject() {
        return investObject;
    }

    public void setInvestObject(String investObject) {
        this.investObject = investObject;
    }

    public String getInvestForm() {
        return investForm;
    }

    public void setInvestForm(String investForm) {
        this.investForm = investForm;
    }

    public Double getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
    }

    public Double getInvestProportion() {
        return investProportion;
    }

    public void setInvestProportion(Double investProportion) {
        this.investProportion = investProportion;
    }

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public Double getThisInvestMoney() {
        return thisInvestMoney;
    }

    public void setThisInvestMoney(Double thisInvestMoney) {
        this.thisInvestMoney = thisInvestMoney;
    }

    public Double getAccumulativeInvestMoney() {
        return accumulativeInvestMoney;
    }

    public void setAccumulativeInvestMoney(Double accumulativeInvestMoney) {
        this.accumulativeInvestMoney = accumulativeInvestMoney;
    }

    public Double getAccumulativeInvestProportion() {
        return accumulativeInvestProportion;
    }

    public void setAccumulativeInvestProportion(Double accumulativeInvestProportion) {
        this.accumulativeInvestProportion = accumulativeInvestProportion;
    }

    public Double getExtractRiskControlMargin() {
        return extractRiskControlMargin;
    }

    public void setExtractRiskControlMargin(Double extractRiskControlMargin) {
        this.extractRiskControlMargin = extractRiskControlMargin;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public String getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(String moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getForecastAllotTime() {
        return forecastAllotTime;
    }

    public void setForecastAllotTime(LocalDate forecastAllotTime) {
        this.forecastAllotTime = forecastAllotTime;
    }

    public Double getExtractRiskRserveRatio() {
        return extractRiskRserveRatio;
    }

    public void setExtractRiskRserveRatio(Double extractRiskRserveRatio) {
        this.extractRiskRserveRatio = extractRiskRserveRatio;
    }

    public Double getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(Double totalFund) {
        this.totalFund = totalFund;
    }

    public Double getHasBeenRaised() {
        return hasBeenRaised;
    }

    public void setHasBeenRaised(Double hasBeenRaised) {
        this.hasBeenRaised = hasBeenRaised;
    }

    public Double getNeedTotalFund() {
        return needTotalFund;
    }

    public void setNeedTotalFund(Double needTotalFund) {
        this.needTotalFund = needTotalFund;
    }
}