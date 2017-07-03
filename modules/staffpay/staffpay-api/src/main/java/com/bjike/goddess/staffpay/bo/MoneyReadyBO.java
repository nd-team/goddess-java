package com.bjike.goddess.staffpay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金准备审核表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 类别
     */
    private String category;

    /**
     * 科目
     */
    private String subject;

    /**
     * 总准备金
     */
    private Double totalReserves;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 比例分配
     */
    private String prorate;

    /**
     * 准备金
     */
    private Double reserves;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserves() {
        return totalReserves;
    }

    public void setTotalReserves(Double totalReserves) {
        this.totalReserves = totalReserves;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProrate() {
        return prorate;
    }

    public void setProrate(String prorate) {
        this.prorate = prorate;
    }

    public Double getReserves() {
        return reserves;
    }

    public void setReserves(Double reserves) {
        this.reserves = reserves;
    }
}