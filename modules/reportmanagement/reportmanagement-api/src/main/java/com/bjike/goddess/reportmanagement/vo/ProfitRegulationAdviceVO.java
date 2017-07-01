package com.bjike.goddess.reportmanagement.vo;

/**
 * 利润增减率分析管理建议设计表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitRegulationAdviceVO {

    /**
     * id
     */
    private String id;
    /**
     * 净利润增减最小值
     */
    private Double netProfitMin;

    /**
     * 净利润增减最大值
     */
    private Double netProfitMax;

    /**
     * 利润总额增减最小值
     */
    private Double profitMin;

    /**
     * 利润总额增减最大值
     */
    private Double profitMax;

    /**
     * 营业利润增减最小值
     */
    private Double incomeMin;

    /**
     * 营业利润增减最大值
     */
    private Double incomeMax;

    /**
     * 管理建议
     */
    private String advice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getNetProfitMin() {
        return netProfitMin;
    }

    public void setNetProfitMin(Double netProfitMin) {
        this.netProfitMin = netProfitMin;
    }

    public Double getNetProfitMax() {
        return netProfitMax;
    }

    public void setNetProfitMax(Double netProfitMax) {
        this.netProfitMax = netProfitMax;
    }

    public Double getProfitMin() {
        return profitMin;
    }

    public void setProfitMin(Double profitMin) {
        this.profitMin = profitMin;
    }

    public Double getProfitMax() {
        return profitMax;
    }

    public void setProfitMax(Double profitMax) {
        this.profitMax = profitMax;
    }

    public Double getIncomeMin() {
        return incomeMin;
    }

    public void setIncomeMin(Double incomeMin) {
        this.incomeMin = incomeMin;
    }

    public Double getIncomeMax() {
        return incomeMax;
    }

    public void setIncomeMax(Double incomeMax) {
        this.incomeMax = incomeMax;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}