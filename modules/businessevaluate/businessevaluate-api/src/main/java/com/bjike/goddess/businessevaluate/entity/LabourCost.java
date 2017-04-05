package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 劳动成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 劳动成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_labourcost")
public class LabourCost extends BaseEntity {

    /**
     * 正常工资
     */
    @Column(name = "normalSalary", columnDefinition = "DECIMAL(10,2)   COMMENT '正常工资'")
    private Double normalSalary;

    /**
     * 加班工资
     */
    @Column(name = "overtimeSalary", columnDefinition = "DECIMAL(10,2)   COMMENT '加班工资'")
    private Double overtimeSalary;

    /**
     * 人员租赁费
     */
    @Column(name = "staffLease", columnDefinition = "DECIMAL(10,2)   COMMENT '人员租赁费'")
    private Double staffLease;

    /**
     * 其他支出
     */
    @Column(name = "another", columnDefinition = "DECIMAL(10,2)   COMMENT '其他支出'")
    private Double another;

    /**
     * 项目信息id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目信息id'")
    private String projectInfoId;

    public Double getNormalSalary() {
        return normalSalary;
    }

    public void setNormalSalary(Double normalSalary) {
        this.normalSalary = normalSalary;
    }

    public Double getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(Double overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public Double getStaffLease() {
        return staffLease;
    }

    public void setStaffLease(Double staffLease) {
        this.staffLease = staffLease;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}