package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 数据业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthsListBO extends BaseBO {

    /**
     * 月份
     */
    private Integer month;
    /**
     * 目标任务量
     */
    private Integer targetWork;

    /**
     * 实际完工量
     */
    private Integer actualWork;

    /**
     * 工作量差异
     */
    private Integer workDifferences;

    /**
     * 参考单价
     */
    private Double price;

    /**
     * 目标收入
     */
    private Double targetIncome;

    /**
     * 计划收入
     */
    private Double planIncome;

    /**
     * 收入差异
     */
    private Double incomeDifferences;
    /**
     * id
     */
    private String id;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTargetWork() {
        return targetWork;
    }

    public void setTargetWork(Integer targetWork) {
        this.targetWork = targetWork;
    }

    public Integer getActualWork() {
        return actualWork;
    }

    public void setActualWork(Integer actualWork) {
        this.actualWork = actualWork;
    }

    public Integer getWorkDifferences() {
        return workDifferences;
    }

    public void setWorkDifferences(Integer workDifferences) {
        this.workDifferences = workDifferences;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(Double targetIncome) {
        this.targetIncome = targetIncome;
    }

    public Double getPlanIncome() {
        return planIncome;
    }

    public void setPlanIncome(Double planIncome) {
        this.planIncome = planIncome;
    }

    public Double getIncomeDifferences() {
        return incomeDifferences;
    }

    public void setIncomeDifferences(Double incomeDifferences) {
        this.incomeDifferences = incomeDifferences;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}