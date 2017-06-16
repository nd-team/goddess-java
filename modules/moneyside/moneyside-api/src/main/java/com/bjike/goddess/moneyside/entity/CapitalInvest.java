package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_capitalinvest")
public class CapitalInvest extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String innerProject;

    /**
     * 开工时间
     */
    @Column(name = "startProjectTime",  columnDefinition = "DATE   COMMENT '开工时间'")
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @Column(name = "endProjectTime",columnDefinition = "DATE   COMMENT '完工时间'")
    private LocalDate endProjectTime;

    /**
     * 项目进度
     */
    @Column(name = "projectProgress", columnDefinition = "VARCHAR(255)   COMMENT '项目进度'")
    private String projectProgress;

    /**
     * 结算进度
     */
    @Column(name = "settlementProgress",  columnDefinition = "VARCHAR(255)   COMMENT '结算进度'")
    private String settlementProgress;

    /**
     * 预估到账时间
     */
    @Column(name = "forecastArriveTime", columnDefinition = "DATE   COMMENT '预估到账时间'")
    private LocalDate forecastArriveTime;

    /**
     * 预估到账金额
     */
    @Column(name = "forecastArriveMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '预估到账金额'")
    private Double forecastArriveMoney;

    /**
     * 到账时间
     */
    @Column(name = "arriveTime",  columnDefinition = "DATE   COMMENT '到账时间'")
    private LocalDate arriveTime;

    /**
     * 到账金额
     */
    @Column(name = "arriveMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '到账金额'")
    private Double arriveMoney;

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 投资总额
     */
    @Column(name = "investTotal", columnDefinition = "DECIMAL(10,2)   COMMENT '投资总额'")
    private Double investTotal;

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
     * 投资占比（投资总额/筹资总额）
     */
    @Column(name = "investProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '投资占比（投资总额/筹资总额）'")
    private Double investProportion;

    /**
     * 风险控制准备金（投资占比*项目风控总金额）
     */
    @Column(name = "riskControlReserves", columnDefinition = "DECIMAL(10,2)   COMMENT '风险控制准备金（投资占比*提取风险控制保证金）'")
    private Double riskControlReserves;

    /**
     * 预估分配额（投资占比*预估到账金额）
     */
    @Column(name = "allocationForecast",  columnDefinition = "DECIMAL(10,2)   COMMENT '预估分配额（投资占比*预估到账金额）'")
    private Double allocationForecast;


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

    public Double getForecastArriveMoney() {
        return forecastArriveMoney;
    }

    public void setForecastArriveMoney(Double forecastArriveMoney) {
        this.forecastArriveMoney = forecastArriveMoney;
    }

    public LocalDate getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDate arriveTime) {
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
}