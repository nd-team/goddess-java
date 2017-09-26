package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_weightals")
public class Weightals extends BaseEntity {

    /**
     * 确定项目提成分配比例时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '确定项目提成分配比例时间'")
    private LocalDateTime time;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 内部项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String project;

    /**
     * 是否立项
     */
    @Column(name = "is_isBuild", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否立项'", insertable = false)
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @Column(name = "buildTime", nullable = false, columnDefinition = "DATETIME   COMMENT '立项时间'")
    private LocalDateTime buildTime;

    /**
     * 是否完工
     */
    @Column(name = "is_isComplete", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否完工'", insertable = false)
    private Boolean isComplete;


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Boolean getIsBuild() {
        return isBuild;
    }

    public void setIsBuild(Boolean isBuild) {
        this.isBuild = isBuild;
    }

    public LocalDateTime getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(LocalDateTime buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }
}