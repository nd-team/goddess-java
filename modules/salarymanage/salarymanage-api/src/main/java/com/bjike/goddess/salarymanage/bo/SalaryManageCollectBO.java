package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 薪资管理汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryManageCollectBO extends BaseBO {

    /**
     * 服从调动安排人数
     */
    private Integer  acceptNumber;

    /**
     * 工资总额
     */
    private Double  allSalary;

    /**
     * 需薪资面谈数
     */
    private Integer  payInterviews;

    /**
     * 实际薪资面谈数
     */
    private Integer  realPayInterviews;

    /**
     * 入职人数
     */
    private Integer  entryNumber;

    /**
     * 入职已确认薪资面谈人数
     */
    private Integer  affirmSalary;

    /**
     * 在职状态人数
     */
    private Integer  onJobNumber;

    /**
     * 待离职人数
     */
    private Integer  waitDimission;

    /**
     * 离职状态人数
     */
    private Integer  dimission;

    /**
     * 转正人数
     */
    private Integer  regularNumber;

    /**
     * 技能等级晋升人数
     */
    private Integer  skillRank;

    /**
     * 管理等级晋升人数
     */
    private Integer  manageRank;

    /**
     * 岗位轮换人数
     */
    private Integer  jobRotation;

    public Integer getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(Integer acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public Double getAllSalary() {
        return allSalary;
    }

    public void setAllSalary(Double allSalary) {
        this.allSalary = allSalary;
    }

    public Integer getPayInterviews() {
        return payInterviews;
    }

    public void setPayInterviews(Integer payInterviews) {
        this.payInterviews = payInterviews;
    }

    public Integer getRealPayInterviews() {
        return realPayInterviews;
    }

    public void setRealPayInterviews(Integer realPayInterviews) {
        this.realPayInterviews = realPayInterviews;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public Integer getAffirmSalary() {
        return affirmSalary;
    }

    public void setAffirmSalary(Integer affirmSalary) {
        this.affirmSalary = affirmSalary;
    }

    public Integer getOnJobNumber() {
        return onJobNumber;
    }

    public void setOnJobNumber(Integer onJobNumber) {
        this.onJobNumber = onJobNumber;
    }

    public Integer getWaitDimission() {
        return waitDimission;
    }

    public void setWaitDimission(Integer waitDimission) {
        this.waitDimission = waitDimission;
    }

    public Integer getDimission() {
        return dimission;
    }

    public void setDimission(Integer dimission) {
        this.dimission = dimission;
    }

    public Integer getRegularNumber() {
        return regularNumber;
    }

    public void setRegularNumber(Integer regularNumber) {
        this.regularNumber = regularNumber;
    }

    public Integer getSkillRank() {
        return skillRank;
    }

    public void setSkillRank(Integer skillRank) {
        this.skillRank = skillRank;
    }

    public Integer getManageRank() {
        return manageRank;
    }

    public void setManageRank(Integer manageRank) {
        this.manageRank = manageRank;
    }

    public Integer getJobRotation() {
        return jobRotation;
    }

    public void setJobRotation(Integer jobRotation) {
        this.jobRotation = jobRotation;
    }
}