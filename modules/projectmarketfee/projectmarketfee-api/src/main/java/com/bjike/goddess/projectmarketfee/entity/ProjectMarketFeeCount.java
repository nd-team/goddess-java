package com.bjike.goddess.projectmarketfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目前期的市场活动费汇总
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 01:50 ]
 * @Description: [ 项目前期的市场活动费汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmarketfee_projectmarketfeecount")
public class ProjectMarketFeeCount extends BaseEntity {

    /**
     * 一级科目
     */
    @Column(name = "firstSubject", columnDefinition = "VARCHAR(255)   COMMENT '一级科目'")
    private String firstSubject;

    /**
     * 二级科目
     */
    @Column(name = "secondSubject", columnDefinition = "VARCHAR(255)   COMMENT '二级科目'")
    private String secondSubject;

    /**
     * 三级科目
     */
    @Column(name = "thirdSubject", columnDefinition = "VARCHAR(255)   COMMENT '三级科目'")
    private String thirdSubject;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 日期
     */
    @Column(name = "voucherDate", columnDefinition = "VARCHAR(255)   COMMENT '日期'")
    private String voucherDate;

    /**
     * 金额
     */
    @Column(name = "borrowMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '金额'")
    private Double borrowMoney;


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

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }
}