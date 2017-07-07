package com.bjike.goddess.fundcheck.vo;

/**
 * 账务支出表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:02 ]
 * @Description: [ 账务支出表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountSpendVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private String date;

    /**
     * 营业费用
     */
    private Double operatExpenses;

    /**
     * 支付给股东(还款)
     */
    private Double payStock;

    /**
     * 其他支出
     */
    private Double otherSpend;

    /**
     * 合计
     */
    private Double total;


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

    public Double getOperatExpenses() {
        return operatExpenses;
    }

    public void setOperatExpenses(Double operatExpenses) {
        this.operatExpenses = operatExpenses;
    }

    public Double getPayStock() {
        return payStock;
    }

    public void setPayStock(Double payStock) {
        this.payStock = payStock;
    }

    public Double getOtherSpend() {
        return otherSpend;
    }

    public void setOtherSpend(Double otherSpend) {
        this.otherSpend = otherSpend;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}