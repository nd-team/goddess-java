package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.AnalyseType;

/**
 * 现金流量分析表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyseVO {

    /**
     * id
     */
    private String id;
    /**
     * 序号
     */
    private Integer num;

    /**
     * 项目
     */
    private String project;

    /**
     * 金额
     */
    private String money;

    /**
     * 比率
     */
    private String rate;

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
     * @return
     */
    private String startTime;

    /**
     * 结束时间
     * @return
     */
    private String endTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
}