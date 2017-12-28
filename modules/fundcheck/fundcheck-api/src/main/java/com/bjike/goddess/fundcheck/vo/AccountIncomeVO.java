package com.bjike.goddess.fundcheck.vo;

/**
 * 账务收入表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:52 ]
 * @Description: [ 账务收入表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountIncomeVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private String date;

    /**
     * 回款
     */
    private Double back;

    /**
     * 股东借款
     */
    private Double stockMoney;

    /**
     * 其他收入
     */
    private Double otherIncome;

    /**
     * 账务收入合计
     */
    private Double money;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBack() {
        return back;
    }

    public void setBack(Double back) {
        this.back = back;
    }

    public Double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(Double stockMoney) {
        this.stockMoney = stockMoney;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}