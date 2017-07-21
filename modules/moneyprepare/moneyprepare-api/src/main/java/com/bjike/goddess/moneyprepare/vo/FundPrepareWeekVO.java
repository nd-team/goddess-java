package com.bjike.goddess.moneyprepare.vo;

import java.io.Serializable;

/**
 * 资金准备表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundPrepareWeekVO implements Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 准备金
     */
    private Double fund;

    /**
     * 项目分配
     */
    private String project;

    /**
     * 时间
     */
    private String time;

    /**
     * 当月前10天的准备金(默认为第一周)
     */
    private Double firstWeek;

    /**
     * 当月前10-15天的准备金(默认为第二周)
     */
    private Double secondWeek;

    /**
     * 当月前15-20天的准备金(默认为第三周)
     */
    private Double thirdWeek;

    /**
     * 当月前20-30天的准备金(默认为第四周)
     */
    private Double fourWeek;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getFirstWeek() {
        return firstWeek;
    }

    public void setFirstWeek(Double firstWeek) {
        this.firstWeek = firstWeek;
    }

    public Double getSecondWeek() {
        return secondWeek;
    }

    public void setSecondWeek(Double secondWeek) {
        this.secondWeek = secondWeek;
    }

    public Double getThirdWeek() {
        return thirdWeek;
    }

    public void setThirdWeek(Double thirdWeek) {
        this.thirdWeek = thirdWeek;
    }

    public Double getFourWeek() {
        return fourWeek;
    }

    public void setFourWeek(Double fourWeek) {
        this.fourWeek = fourWeek;
    }
}