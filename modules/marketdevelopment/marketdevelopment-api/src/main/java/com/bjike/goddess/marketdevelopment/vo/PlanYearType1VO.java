package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.MoneyType;
import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 年计划表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearType1VO {

    /**
     * id
     */
    private String id;
    /**
     * 年份id
     */
    private String yearId;

    /**
     * 类型
     */
    private MoneyType moneyType;

//    /**
//     * 月份
//     */
//    private MonthType monthType;

    /**
     * 一月
     */
    private Double jan;

    /**
     * 二月
     */
    private Double feb;

    /**
     * 三月
     */
    private Double mar;

    /**
     * 四月
     */
    private Double apr;

    /**
     * 五月
     */
    private Double may;

    /**
     * 六月
     */
    private Double jun;

    /**
     * 七月
     */
    private Double jul;

    /**
     * 八月
     */
    private Double aug;

    /**
     * 九月
     */
    private Double sep;

    /**
     * 十月
     */
    private Double oct;

    /**
     * 十一月
     */
    private Double nov;

    /**
     * 十二月
     */
    private Double dec;

//    /**
//     * 金额
//     */
//    private Double money;

    /**
     * 合计
     */
    private String total;

    /**
     * 状态
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getJan() {
        return jan;
    }

    public void setJan(Double jan) {
        this.jan = jan;
    }

    public Double getFeb() {
        return feb;
    }

    public void setFeb(Double feb) {
        this.feb = feb;
    }

    public Double getMar() {
        return mar;
    }

    public void setMar(Double mar) {
        this.mar = mar;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJun() {
        return jun;
    }

    public void setJun(Double jun) {
        this.jun = jun;
    }

    public Double getJul() {
        return jul;
    }

    public void setJul(Double jul) {
        this.jul = jul;
    }

    public Double getAug() {
        return aug;
    }

    public void setAug(Double aug) {
        this.aug = aug;
    }

    public Double getSep() {
        return sep;
    }

    public void setSep(Double sep) {
        this.sep = sep;
    }

    public Double getOct() {
        return oct;
    }

    public void setOct(Double oct) {
        this.oct = oct;
    }

    public Double getNov() {
        return nov;
    }

    public void setNov(Double nov) {
        this.nov = nov;
    }

    public Double getDec() {
        return dec;
    }

    public void setDec(Double dec) {
        this.dec = dec;
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