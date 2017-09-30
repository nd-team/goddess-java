package com.bjike.goddess.dataprogress.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资料收集进度管理主表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dataprogress_dataprogressmain")
public class DataProgressMain extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 任务下达人
     */
    @Column(name = "missionRelease",  columnDefinition = "VARCHAR(255)   COMMENT '任务下达人'")
    private String missionRelease;

    /**
     * 计划收集时间
     */
    @Column(name = "planCollectionTime",  columnDefinition = "DATE   COMMENT '计划收集时间'")
    private LocalDate planCollectionTime;

    /**
     * 专业类别
     */
    @Column(name = "majorKind",  columnDefinition = "VARCHAR(255)   COMMENT '专业类别'")
    private String majorKind;

    /**
     * 专业方向
     */
    @Column(name = "majorDirection",  columnDefinition = "VARCHAR(255)   COMMENT '专业方向'")
    private String majorDirection;

    /**
     * 技能
     */
    @Column(name = "skill",  columnDefinition = "VARCHAR(255)   COMMENT '技能'")
    private String skill;

    /**
     * 厂商
     */
    @Column(name = "manufacturer",  columnDefinition = "VARCHAR(255)   COMMENT '厂商'")
    private String manufacturer;


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

    public String getMissionRelease() {
        return missionRelease;
    }

    public void setMissionRelease(String missionRelease) {
        this.missionRelease = missionRelease;
    }

    public LocalDate getPlanCollectionTime() {
        return planCollectionTime;
    }

    public void setPlanCollectionTime(LocalDate planCollectionTime) {
        this.planCollectionTime = planCollectionTime;
    }

    public String getMajorKind() {
        return majorKind;
    }

    public void setMajorKind(String majorKind) {
        this.majorKind = majorKind;
    }

    public String getMajorDirection() {
        return majorDirection;
    }

    public void setMajorDirection(String majorDirection) {
        this.majorDirection = majorDirection;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}