package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 成本明细汇总业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectBO extends BaseBO {

    /**
     * 分类
     */
    private String type;

    /**
     * 一月
     */
    private Double january;

    /**
     * 二月
     */
    private Double february;

    /**
     * 三月
     */
    private Double march;

    /**
     * 四月
     */
    private Double april;

    /**
     * 五月
     */
    private Double may;

    /**
     * 六月
     */
    private Double june;

    /**
     * 七月
     */
    private Double july;

    /**
     * 八月
     */
    private Double aguest;

    /**
     * 九月
     */
    private Double september;

    /**
     * 十月
     */
    private Double october;

    /**
     * 十一月
     */
    private Double november;

    /**
     * 十二月
     */
    private Double december;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMarch() {
        return march;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAguest() {
        return aguest;
    }

    public void setAguest(Double aguest) {
        this.aguest = aguest;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
    }
}