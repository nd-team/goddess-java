package com.bjike.goddess.salarymanage.vo;

/**
* 薪资测算结果表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryCalculateResultVO {
    /**
     * id
     */
    private String  id;

    /**
     * 最低期望
     */
    private Integer  minExpectation;

    /**
     * 最高期望
     */
    private Integer  maxExpectation;

    /**
     * 平均期望值
     */
    private Double  averageExpectation;

    /**
     * 区间
     */
    private Integer  interval;

    /**
     * 总区间
     */
    private Integer  totalInterval;

    /**
     * 技能等级份额
     */
    private Integer  skillRankLot;


    /**
     * 管理等级份额
     */
    private Integer  manageRankLot;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMinExpectation() {
        return minExpectation;
    }

    public void setMinExpectation(Integer minExpectation) {
        this.minExpectation = minExpectation;
    }

    public Integer getMaxExpectation() {
        return maxExpectation;
    }

    public void setMaxExpectation(Integer maxExpectation) {
        this.maxExpectation = maxExpectation;
    }

    public Double getAverageExpectation() {
        return averageExpectation;
    }

    public void setAverageExpectation(Double averageExpectation) {
        this.averageExpectation = averageExpectation;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getTotalInterval() {
        return totalInterval;
    }

    public void setTotalInterval(Integer totalInterval) {
        this.totalInterval = totalInterval;
    }

    public Integer getSkillRankLot() {
        return skillRankLot;
    }

    public void setSkillRankLot(Integer skillRankLot) {
        this.skillRankLot = skillRankLot;
    }

    public Integer getManageRankLot() {
        return manageRankLot;
    }

    public void setManageRankLot(Integer manageRankLot) {
        this.manageRankLot = manageRankLot;
    }
}