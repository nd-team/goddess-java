package com.bjike.goddess.fundcheck.vo;

/**
 * 期初余额表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BeginBalanceVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private String date;

    /**
     * 期初余额
     */
    private Double beginBalance;


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

    public Double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
}