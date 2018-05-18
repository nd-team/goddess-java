package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.MoneyType;
import com.bjike.goddess.marketdevelopment.enums.MonthType;
import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 年计划业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearTypeBO extends BaseBO {

    /**
     * 年份id
     */
    private String yearId;

    /**
     * 类型
     */
    private MoneyType moneyType;

    /**
     * 月份
     */
    private MonthType monthType;

    /**
     * 金额
     */
    private Double money;

    /**
     * 合计
     */
    private String total;

    /**
     * 状态
     */
    private Status status;


    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public MonthType getMonthType() {
        return monthType;
    }

    public void setMonthType(MonthType monthType) {
        this.monthType = monthType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}