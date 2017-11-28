package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.AnalyseType;

/**
 * 现金流量分析业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyseManaVO extends BaseBO {

    /**
     * 序号
     */
    private Integer num;

    /**
     * 项目
     */
    private String project;

    /**
     * 本期金额
     */
    private String money;

    /**
     * 上期金额
     */
    private String money1;

//    /**
//     * 比率
//     */
//    private String rate;

    /**
     * 分析类型
     */
    private AnalyseType analyseType;

    /**
     * 分析
     */
    private String analyse;

    /**
     * 管理建议
     */
    private String advice;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 增长额
     */
    private Double growthAmount;

    /**
     * 增长率
     */
    private String growthRate;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public AnalyseType getAnalyseType() {
        return analyseType;
    }

    public void setAnalyseType(AnalyseType analyseType) {
        this.analyseType = analyseType;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getGrowthAmount() {
        return growthAmount;
    }

    public void setGrowthAmount(Double growthAmount) {
        this.growthAmount = growthAmount;
    }

    public String getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }
}