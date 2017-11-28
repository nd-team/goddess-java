package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.AnalyseType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 现金流量分析
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashanalyse")
public class CashAnalyse extends BaseEntity {

    /**
     * 序号
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private Integer num;

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 金额
     */
    @Column(name = "money", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '金额'")
    private String money;

    /**
     * 比率
     */
    @Column(name = "rate", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '比率'")
    private String rate;

    /**
     * 分析类型
     */
    @Column(name = "analyseType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分析类型'")
    private AnalyseType analyseType;

    /**
     * 分析
     */
    @Column(name = "analyse", columnDefinition = "VARCHAR(255)   COMMENT '分析'")
    private String analyse;

    /**
     * 管理建议
     */
    @Column(name = "advice", columnDefinition = "VARCHAR(255)   COMMENT '管理建议'")
    private String advice;

    /**
     * 开始时间
     * @return
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开始时间'")
    private String startTime;

    /**
     * 结束时间
     * @return
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '结束时间'")
    private String endTime;



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