package com.bjike.goddess.staffshares.vo;

/**
 * 交易持股明细表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 交易持股明细表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DividendsDetailVO {
    /**
     * id
     */
    private String id;


    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 持股人
     */
    private String shareholder;

//    /**
//     * 税后利润
//     */
//    private Double taxProfit;

    /**
     * 持股数
     */
    private int num;

    /**
     * 总股本
     */
    private Double totalEquity;

    /**
     * 总收益/
     */
    private Double totalEarnings;

    /**
     * 持股时间/购入时间
     */
    private String buyTime;

    /**
     * 持股状态
     */
    private String status;

    /**
     * 分红状态
     */
    private Boolean situation;

    /**
     * 分红收益
     */
    private Double earnings;


    /**
     * 分红时间
     */
    private String dividendTime;

//    /**
//     * 本次红利收益时间段
//     */
//    private String time;
//
//    /**
//     * 备注
//     */
//    private String remark;
//
//    /**
//     * 持股人确认情况
//     */
//    private Boolean situation;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSituation() {
        return situation;
    }

    public void setSituation(Boolean situation) {
        this.situation = situation;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getDividendTime() {
        return dividendTime;
    }

    public void setDividendTime(String dividendTime) {
        this.dividendTime = dividendTime;
    }
}