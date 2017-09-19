package com.bjike.goddess.assistance.vo;

/**
 * 工龄补助标准表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SenioritySubsidiesStandardVO {

    /**
     * id
     */
    private String id;
    /**
     * 年限
     */
    private String yearNum;

    /**
     * 月份
     */
    private String month;

    /**
     * 每月补助金额（元)
     */
    private Double perMonthGrant;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getPerMonthGrant() {
        return perMonthGrant;
    }

    public void setPerMonthGrant(Double perMonthGrant) {
        this.perMonthGrant = perMonthGrant;
    }
}