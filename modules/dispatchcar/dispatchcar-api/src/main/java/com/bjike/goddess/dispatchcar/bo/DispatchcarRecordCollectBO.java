package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 出车记录管理汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class DispatchcarRecordCollectBO extends BaseBO {
    /**
     * 项目出车数量
     */
    private Integer  dispatchcarNumber;

    /**
     * 已有出车记录数
     */
    private Integer  hasCarRecordNumber;

    /**
     * 人工录入出车单数
     */
    private Integer  peopleTypeNumber;

    /**
     * 有问题出车记录数
     */
    private Integer  hasProblemNumber;

    /**
     * 已解决出车记录问题数
     */
    private Integer  alreadySolveNumber;

    /**
     * 负责人已审核数
     */
    private Integer  audityNumber;

    /**
     * 预算模块核对数
     */
    private Integer  budgetNumber;

    /**
     * 账务模块核对数
     */
    private Integer  accountNumber;

    /**
     * 资金模块核对数
     */
    private Integer  moneyAccountNumber;

    /**
     * 租车费
     */
    private Double  rentcarCost;

    /**
     * 停车费
     */
    private Double  parkCost;

    /**
     * 过路费
     */
    private Double  roadCost;

    /**
     * 餐补费
     */
    private Double  mealCost;

    /**
     * 已付款出车数
     */
    private Integer  payedRentcarNumber;

    /**
     * 已付款出车费
     */
    private Double  payedRentcarCost;

    public Integer getDispatchcarNumber() {
        return dispatchcarNumber;
    }

    public void setDispatchcarNumber(Integer dispatchcarNumber) {
        this.dispatchcarNumber = dispatchcarNumber;
    }

    public Integer getHasCarRecordNumber() {
        return hasCarRecordNumber;
    }

    public void setHasCarRecordNumber(Integer hasCarRecordNumber) {
        this.hasCarRecordNumber = hasCarRecordNumber;
    }

    public Integer getPeopleTypeNumber() {
        return peopleTypeNumber;
    }

    public void setPeopleTypeNumber(Integer peopleTypeNumber) {
        this.peopleTypeNumber = peopleTypeNumber;
    }

    public Integer getHasProblemNumber() {
        return hasProblemNumber;
    }

    public void setHasProblemNumber(Integer hasProblemNumber) {
        this.hasProblemNumber = hasProblemNumber;
    }

    public Integer getAlreadySolveNumber() {
        return alreadySolveNumber;
    }

    public void setAlreadySolveNumber(Integer alreadySolveNumber) {
        this.alreadySolveNumber = alreadySolveNumber;
    }

    public Integer getAudityNumber() {
        return audityNumber;
    }

    public void setAudityNumber(Integer audityNumber) {
        this.audityNumber = audityNumber;
    }

    public Integer getBudgetNumber() {
        return budgetNumber;
    }

    public void setBudgetNumber(Integer budgetNumber) {
        this.budgetNumber = budgetNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getMoneyAccountNumber() {
        return moneyAccountNumber;
    }

    public void setMoneyAccountNumber(Integer moneyAccountNumber) {
        this.moneyAccountNumber = moneyAccountNumber;
    }

    public Integer getPayedRentcarNumber() {
        return payedRentcarNumber;
    }

    public void setPayedRentcarNumber(Integer payedRentcarNumber) {
        this.payedRentcarNumber = payedRentcarNumber;
    }


    public Double getRentcarCost() {
        return rentcarCost;
    }

    public void setRentcarCost(Double rentcarCost) {
        this.rentcarCost = rentcarCost;
    }

    public Double getParkCost() {
        return parkCost;
    }

    public void setParkCost(Double parkCost) {
        this.parkCost = parkCost;
    }

    public Double getRoadCost() {
        return roadCost;
    }

    public void setRoadCost(Double roadCost) {
        this.roadCost = roadCost;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
    }

    public Double getPayedRentcarCost() {
        return payedRentcarCost;
    }

    public void setPayedRentcarCost(Double payedRentcarCost) {
        this.payedRentcarCost = payedRentcarCost;
    }
}