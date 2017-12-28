package com.bjike.goddess.otherexpenses.vo;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-03 15:42]
 * @Description: [ 地区汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCollectVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 目标其他费用
     */
    private Double target;

    /**
     * 实际其他费用
     */
    private Double actual;

    /**
     * 比例
     */
    private Double ratio;

    /**
     * 差额
     */
    private Double balance;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
