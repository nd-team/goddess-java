package com.bjike.goddess.capability.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 个人能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "capability_selfcapability")
public class SelfCapability extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 个人资质
     */
    @Column(name = "capacity",  columnDefinition = "VARCHAR(255)   COMMENT '个人资质'")
    private String capacity;

    /**
     * 个人职称
     */
    @Column(name = "selfJobTitle",  columnDefinition = "VARCHAR(255)   COMMENT '个人职称'")
    private String selfJobTitle;

    /**
     * 岗位职称
     */
    @Column(name = "positionTitle",  columnDefinition = "VARCHAR(255)   COMMENT '岗位职称'")
    private String positionTitle;

    /**
     * 工作年限
     */
    @Column(name = "workYear",  columnDefinition = "VARCHAR(255)   COMMENT '工作年限'")
    private String workYear;

    /**
     * 个人经手项目
     */
    @Column(name = "selfProject",  columnDefinition = "VARCHAR(255)   COMMENT '个人经手项目'")
    private String selfProject;

    /**
     * 个人社交资源姓名
     */
    @Column(name = "contactName",  columnDefinition = "VARCHAR(255)   COMMENT '个人社交资源姓名'")
    private String contactName;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSelfJobTitle() {
        return selfJobTitle;
    }

    public void setSelfJobTitle(String selfJobTitle) {
        this.selfJobTitle = selfJobTitle;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getSelfProject() {
        return selfProject;
    }

    public void setSelfProject(String selfProject) {
        this.selfProject = selfProject;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }



}