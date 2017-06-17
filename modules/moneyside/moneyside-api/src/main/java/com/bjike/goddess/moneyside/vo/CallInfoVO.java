package com.bjike.goddess.moneyside.vo;

/**
 * 招投信息列表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CallInfoVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}