package com.bjike.goddess.fundcheck.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 支付给股东业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PayStockBO extends BaseBO {

    /**
     * 日期
     */
    private String date;
    /**
     * 一级
     */
    private String firstName;
    /**
     * 二级
     */
    private String secondName;

    /**
     * 股东名
     */
    private String stockName;

    /**
     * 金额
     */
    private Double money;

    /**
     * 股东名
     */
    private List<String> stockNameList;

    /**
     * 金额
     */
    private List<Double> stockNameMoney;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public List<String> getStockNameList() {
        return stockNameList;
    }

    public void setStockNameList(List<String> stockNameList) {
        this.stockNameList = stockNameList;
    }

    public List<Double> getStockNameMoney() {
        return stockNameMoney;
    }

    public void setStockNameMoney(List<Double> stockNameMoney) {
        this.stockNameMoney = stockNameMoney;
    }
}