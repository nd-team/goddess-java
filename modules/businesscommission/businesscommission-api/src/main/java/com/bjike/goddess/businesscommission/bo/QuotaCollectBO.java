package com.bjike.goddess.businesscommission.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 业务提成权重分配表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuotaCollectBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 已立项
     */
    private Boolean isBuild;

    /**
     * 已确定提成分配比例项目数
     */
    private Integer num;

    /**
     * 已协商业务提成分配比例项目数
     */
    private Integer ratioNum;

    /**
     * 已完工项目
     */
    private String completeNum;

    /**
     * 已回款项目
     */
    private String items;

    /**
     * 目标业务提成额
     */
    private Double aimAmount;

    /**
     * 计划业务提成额
     */
    private Double planAmount;

    /**
     * 实际业务提成额
     */
    private Double actualAmount;

    /**
     * 差异提成额
     */
    private Double differenceAmount;

    /**
     * 剩余占额
     */
    private Double remainingAccount;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRatioNum() {
        return ratioNum;
    }

    public void setRatioNum(Integer ratioNum) {
        this.ratioNum = ratioNum;
    }

    public String getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(String completeNum) {
        this.completeNum = completeNum;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Double getAimAmount() {
        return aimAmount;
    }

    public void setAimAmount(Double aimAmount) {
        this.aimAmount = aimAmount;
    }

    public Double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(Double planAmount) {
        this.planAmount = planAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Double getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(Double differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    public Double getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(Double remainingAccount) {
        this.remainingAccount = remainingAccount;
    }
}