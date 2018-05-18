package com.bjike.goddess.moneyprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 资金准备
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyprepare_fundprepare")
public class FundPrepare extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '时间'")
    private String time;

    /**
     * 一级科目
     */
    @Column(name = "firstSubject", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 准备金
     */
    @Column(name = "fund", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double fund;

    /**
     * 项目分配
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目分配'")
    private String project;


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
}