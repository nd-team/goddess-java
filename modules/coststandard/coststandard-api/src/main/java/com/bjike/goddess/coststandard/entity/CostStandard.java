package com.bjike.goddess.coststandard.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 费用标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "coststandard_coststandard")
public class CostStandard extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String name;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 标准
     */
    @Column(name = "standard", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private String standard;

    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 适用部门或项目组
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '适用部门或项目组'")
    private String department;

    /**
     * 制定日期
     */
    @Column(name = "draftDate", nullable = false, columnDefinition = "DATE   COMMENT '制定日期'")
    private LocalDate draftDate;

    /**
     * 使用状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '使用状态'")
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(LocalDate draftDate) {
        this.draftDate = draftDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}