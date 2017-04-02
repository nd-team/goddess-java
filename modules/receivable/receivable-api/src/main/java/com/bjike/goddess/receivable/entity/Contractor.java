package com.bjike.goddess.receivable.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 承包商列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "receivable_contractor")
public class Contractor extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String name;

    /**
     * 百分比
     */
    @Column(name = "percent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '百分比'")
    private Double percent;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "creationTime", nullable = false, columnDefinition = "DATE   COMMENT '创建时间'")
    private LocalDate creationTime;

    /**
     * 状态
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}