package com.bjike.goddess.regularization.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总图形展示业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 汇总图形展示业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FigureShowBO extends BaseBO {

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 入职人数
     */
    private Integer entryNum;

    /**
     * 三天需跟进收集意见数
     */
    private Integer threeFollowNum;

    /**
     * 一周需跟进收集意见数
     */
    private Integer weekFollowNum;

    /**
     * 一个月需跟进收集意见数
     */
    private Integer monthFollowNum;

    /**
     * 待转正人数
     */
    private Integer stayPositiveNum;

    /**
     * 申请转正人数
     */
    private Integer applyPositiveNum;

    /**
     * 已转正人数
     */
    private Integer hasPositiveNum;

    /**
     * 已转正面谈人数
     */
    private Integer noInterviewNum;

    /**
     * 未转正人数
     */
    private Integer noPositiveNum;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Integer getThreeFollowNum() {
        return threeFollowNum;
    }

    public void setThreeFollowNum(Integer threeFollowNum) {
        this.threeFollowNum = threeFollowNum;
    }

    public Integer getWeekFollowNum() {
        return weekFollowNum;
    }

    public void setWeekFollowNum(Integer weekFollowNum) {
        this.weekFollowNum = weekFollowNum;
    }

    public Integer getMonthFollowNum() {
        return monthFollowNum;
    }

    public void setMonthFollowNum(Integer monthFollowNum) {
        this.monthFollowNum = monthFollowNum;
    }

    public Integer getStayPositiveNum() {
        return stayPositiveNum;
    }

    public void setStayPositiveNum(Integer stayPositiveNum) {
        this.stayPositiveNum = stayPositiveNum;
    }

    public Integer getApplyPositiveNum() {
        return applyPositiveNum;
    }

    public void setApplyPositiveNum(Integer applyPositiveNum) {
        this.applyPositiveNum = applyPositiveNum;
    }

    public Integer getHasPositiveNum() {
        return hasPositiveNum;
    }

    public void setHasPositiveNum(Integer hasPositiveNum) {
        this.hasPositiveNum = hasPositiveNum;
    }

    public Integer getNoInterviewNum() {
        return noInterviewNum;
    }

    public void setNoInterviewNum(Integer noInterviewNum) {
        this.noInterviewNum = noInterviewNum;
    }

    public Integer getNoPositiveNum() {
        return noPositiveNum;
    }

    public void setNoPositiveNum(Integer noPositiveNum) {
        this.noPositiveNum = noPositiveNum;
    }
}