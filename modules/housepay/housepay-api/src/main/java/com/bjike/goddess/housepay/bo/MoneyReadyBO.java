package com.bjike.goddess.housepay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金准备审核表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyBO extends BaseBO {

    /**
     * 时间
     */
    private String time;

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


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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