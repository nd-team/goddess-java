package com.bjike.goddess.bonusmoneyperparepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 奖金资金准备与支付业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备与支付业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyPerpareBO extends BaseBO {

    /**
     * 年份
     */
    private Integer years;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 科目
     */
    private String subjects;

    /**
     * 总准备金
     */
    private Double totalReserve;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 比例分配
     */
    private Double proportional;

    /**
     * 准备金
     */
    private Double reserve;


    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getProportional() {
        return proportional;
    }

    public void setProportional(Double proportional) {
        this.proportional = proportional;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }
}