package com.bjike.goddess.staffshares.vo;

/**
 * 干股分红表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DividendsVO {

    /**
     * 持股人
     */
    private String shareholder;

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
     * 税后利润
     */
    private Double taxProfit;

    /**
     * 持股数
     */
    private int num;

    /**
     * 总股本
     */
    private Double totalEquity;

    /**
     * 每股收益
     */
    private Double earnings;

    /**
     * 总收益/分红
     */
    private Double totalEarnings;

    /**
     * 购入时间
     */
    private String buyTime;

    /**
     * 持股时长
     */
    private int duration;

    /**
     * 分红发放时间
     */
    private String dividendTime;

    /**
     * 本次红利收益时间段
     */
    private String time;

    /**
     * 备注
     */
    private String remark;

    /**
     * 持股人确认情况
     */
    private Boolean situation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getTaxProfit() {
        return taxProfit;
    }

    public void setTaxProfit(Double taxProfit) {
        this.taxProfit = taxProfit;
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

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDividendTime() {
        return dividendTime;
    }

    public void setDividendTime(String dividendTime) {
        this.dividendTime = dividendTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSituation() {
        return situation;
    }

    public void setSituation(Boolean situation) {
        this.situation = situation;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }
}