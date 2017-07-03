package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 招投信息列表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CallInfoBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目内部名称
     */
    private String innerProject;

    /**
     * 开工时间
     */
    private String startProjectTime;

    /**
     * 完工时间
     */
    private String endProjectTime;

    /**
     * 合同金额
     */
    private Double money;

    /**
     * 项目负责人
     */
    private String projectCharge;

    /**
     * 合同单价
     */
    private Double price;

    /**
     * 合同规模
     */
    private String size;

    /**
     * 是否签订合同
     */
    private Boolean signedContract;

    /**
     * 回款周期
     */
    private String paybackPeriod;

    /**
     * 预估到账时间
     */
    private String forecastArriveTime;

    /**
     * 承包商
     */
    private String contractor;

    /**
     * 难易程度
     */
    private String complexity;

    /**
     * 预估利润率
     */
    private Double forecastProfitMargin;

    /**
     * 资金方分配率
     */
    private Double capitalAlloRate;

    /**
     * 预估投资收益率
     */
    private Double forecastReturnInvestment;

    /**
     * 预估分配时间
     */
    private String forecastAllotTime;

    /**
     * 提取风险准备金率
     */
    private Double extractRiskRserveRatio;

    /**
     * 筹资总额
     */
    private Double totalFund;

    /**
     * 已筹资金额
     */
    private Double hasBeenRaised;

    /**
     * 还需筹资总额(筹资总额-已筹资金额)
     */
    private Double needTotalFund;
    /**
     * 投资人
     */
    private String investor;

    /**
     * 投资对象
     */
    private String investObject;

    /**
     * 投资形式
     */
    private String investForm;

    /**
     * 投资总额
     */
    private Double investTotal;

    /**
     * 投资占比（投资总额/筹资总额）
     */
    private Double investProportion;

    /**
     * 投资日期
     */
    private String investDate;

    /**
     * 本次投资额
     */
    private Double thisInvestMoney;


    /**
     * 累计投资额
     */
    private Double accumulativeInvestMoney;

    /**
     * 累计投资比（累计投资额/投资总额）
     */
    private Double accumulativeInvestProportion;

    /**
     * 提取风险控制保证金（本次投资额/提取风险准备金率）
     */
    private Double extractRiskControlMargin;

    /**
     * 打款账户
     */
    private String moneyAccountName;

    /**
     * 打款账号
     */
    private String moneyAccount;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


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

    public String getForecastArriveTime() {
        return forecastArriveTime;
    }

    public void setForecastArriveTime(String forecastArriveTime) {
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

    public String getForecastAllotTime() {
        return forecastAllotTime;
    }

    public void setForecastAllotTime(String forecastAllotTime) {
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

    public String getInvestDate() {
        return investDate;
    }

    public void setInvestDate(String investDate) {
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