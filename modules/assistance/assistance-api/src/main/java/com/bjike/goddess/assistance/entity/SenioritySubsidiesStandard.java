package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工龄补助标准
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_senioritysubsidiesstandard")
public class SenioritySubsidiesStandard extends BaseEntity {

    /**
     * 年限
     */
    @Column(name = "yearNum", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '年限'")
    private String yearNum;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '月份'")
    private String month;

    /**
     * 每月补助金额（元)
     */
    @Column(name = "perMonthGrant", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '每月补助金额（元)'")
    private Double perMonthGrant;


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