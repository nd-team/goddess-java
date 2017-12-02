package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 周计划的周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_weekcycle")
public class WeekCycle extends BaseEntity {

    /**
     * 业务方向科目id
     */
    @Column(name = "subjectDataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目id'")
    private String subjectDataId;

    /**
     * 周期
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '周期'")
    private String cycle;

    /**
     * 各周工作量在整月占比
     */
    @Column(name = "radio", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '各周工作量在整月占比'")
    private String radio;


    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }
}