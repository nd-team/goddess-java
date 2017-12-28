package com.bjike.goddess.moneyside.vo;

/**
 * 资金投资表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CapitalInvestVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
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
     * 项目进度
     */
    private String projectProgress;

    /**
     * 结算进度
     */
    private String settlementProgress;

    /**
     * 预估到账时间
     */
    private String forecastArriveTime;

    /**
     * 预估到账金额
     */
    private Double forecastArriveMoney;

    /**
     * 到账时间
     */
    private String arriveTime;

    /**
     * 到账金额
     */
    private Double arriveMoney;

    /**
     * 投资人
     */
    private String investor;

    /**
     * 投资总额
     */
    private Double investTotal;

    /**
     * 本次投资额
     */
    private Double thisInvestMoney;

    /**
     * 累计投资额
     */
    private Double accumulativeInvestMoney;

    /**
     * 投资占比（本次投资额/筹资总额）
     */
    private Double investProportion;

    /**
     * 风险控制准备金（投资占比*项目风控总金额）
     */
    private Double riskControlReserves;

    /**
     * 预估分配额（投资占比*预估到账金额）
     */
    private Double allocationForecast;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getForecastArriveMoney() {
        return forecastArriveMoney;
    }

    public void setForecastArriveMoney(Double forecastArriveMoney) {
        this.forecastArriveMoney = forecastArriveMoney;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Double getArriveMoney() {
        return arriveMoney;
    }

    public void setArriveMoney(Double arriveMoney) {
        this.arriveMoney = arriveMoney;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Double getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(Double investTotal) {
        this.investTotal = investTotal;
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

    public Double getInvestProportion() {
        return investProportion;
    }

    public void setInvestProportion(Double investProportion) {
        this.investProportion = investProportion;
    }

    public Double getRiskControlReserves() {
        return riskControlReserves;
    }

    public void setRiskControlReserves(Double riskControlReserves) {
        this.riskControlReserves = riskControlReserves;
    }

    public Double getAllocationForecast() {
        return allocationForecast;
    }

    public void setAllocationForecast(Double allocationForecast) {
        this.allocationForecast = allocationForecast;
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