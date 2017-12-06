package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.DateType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 日期数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_datedata")
public class DateData extends BaseEntity {

    /**
     * 周期id
     */
    @Column(name = "cycleId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '周期id'")
    private String cycleId;

    /**
     * 日期
     */
    @Column(name = "date", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '日期'")
    private String date;

    /**
     * 类型
     */
    @Column(name = "dateType", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0   COMMENT '类型'")
    private DateType dateType;

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

}