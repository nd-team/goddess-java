package com.bjike.goddess.fundcheck.vo;

import java.util.List;

/**
 * 收到股东款表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StockMoneyVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private String date;

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