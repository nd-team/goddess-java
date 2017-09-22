package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 薪资测算结果业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryCalculateResultBO extends BaseBO {
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
     * 技能等级份额
     */
    private Integer  skillRankLot;



    /**
     * 管理等级份额
     */
    private Integer  manageRankLot;


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