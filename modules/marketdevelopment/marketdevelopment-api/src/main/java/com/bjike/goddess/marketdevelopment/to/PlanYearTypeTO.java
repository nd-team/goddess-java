package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.MoneyType;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.validation.constraints.NotNull;

/**
 * 年计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearTypeTO extends BaseTO {

//    /**
//     * 年份id
//     */
//    private String yearId;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private MoneyType moneyType;

//    /**
//     * 月份
//     */
//    private MonthType monthType;

    /**
     * 一月
     */
    @NotNull(message = "一月不能为空", groups = {ADD.class, EDIT.class})
    private Double jan;

    /**
     * 二月
     */
    @NotNull(message = "二月不能为空", groups = {ADD.class, EDIT.class})
    private Double feb;

    /**
     * 三月
     */
    @NotNull(message = "三月不能为空", groups = {ADD.class, EDIT.class})
    private Double mar;

    /**
     * 四月
     */
    @NotNull(message = "四月不能为空", groups = {ADD.class, EDIT.class})
    private Double apr;

    /**
     * 五月
     */
    @NotNull(message = "五月不能为空", groups = {ADD.class, EDIT.class})
    private Double may;

    /**
     * 六月
     */
    @NotNull(message = "六月不能为空", groups = {ADD.class, EDIT.class})
    private Double jun;

    /**
     * 七月
     */
    @NotNull(message = "七月不能为空", groups = {ADD.class, EDIT.class})
    private Double jul;

    /**
     * 八月
     */
    @NotNull(message = "八月不能为空", groups = {ADD.class, EDIT.class})
    private Double aug;

    /**
     * 九月
     */
    @NotNull(message = "九月不能为空", groups = {ADD.class, EDIT.class})
    private Double sep;

    /**
     * 十月
     */
    @NotNull(message = "十月不能为空", groups = {ADD.class, EDIT.class})
    private Double oct;

    /**
     * 十一月
     */
    @NotNull(message = "十一月不能为空", groups = {ADD.class, EDIT.class})
    private Double nov;

    /**
     * 十二月
     */
    @NotNull(message = "十二月不能为空", groups = {ADD.class, EDIT.class})
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