package com.bjike.goddess.oilcardprepared.vo;

/**
 * 等待付款表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayVO {

    /**
     * id
     */
    private String id;
    /**
     * 油卡编号
     */
    private String oilCardCode;

    /**
     * 卡号
     */
    private String oilCardNumber;

    /**
     * 充值日期
     */
    private String rechargeDate;

    /**
     * 充值金额
     */
    private Double rechargeMoney;

    /**
     * 充值人
     */
    private String rechargeUser;

    /**
     * 充值方式
     */
    private String rechargeWay;

    /**
     * 初始余额
     */
    private Double oilCardBalance;

    /**
     * 是否付款
     */
    private Boolean pay;

    /**
     * 充值金额总额
     */
    private Double rechargeMoneySum;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 年份
     */
    private Integer year;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRechargeMoneySum() {
        return rechargeMoneySum;
    }

    public void setRechargeMoneySum(Double rechargeMoneySum) {
        this.rechargeMoneySum = rechargeMoneySum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(String rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }
}