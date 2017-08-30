package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 团体意外险被保险人信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_groupbyinsurer")
public class GroupByInsurer extends BaseEntity {

    /**
     * 被保险人姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '被保险人姓名'")
    private String name;

    /**
     * 证件号码
     */
    @Column(name = "fileNumber",  columnDefinition = "VARCHAR(255)   COMMENT '证件号码'")
    private String fileNumber;

    /**
     * 性别
     */
    @Column(name = "sex",  columnDefinition = "VARCHAR(255)   COMMENT '性别(男/女)'")
    private String sex;

    /**
     * 投保年龄
     */
    @Column(name = "payAge",  columnDefinition = "VARCHAR(255)   COMMENT '投保年龄'")
    private String payAge;

    /**
     * 层级
     */
    @Column(name = "level",  columnDefinition = "VARCHAR(255)   COMMENT '层级'")
    private String level;

    /**
     * 受益人
     */
    @Column(name = "benefiter",  columnDefinition = "VARCHAR(255)   COMMENT '受益人'")
    private String benefiter;

    /**
     * 主被保人姓名
     */
    @Column(name = "majorName",  columnDefinition = "VARCHAR(255)   COMMENT '主被保人姓名'")
    private String majorName;

    /**
     * 与主被保人关系
     */
    @Column(name = "relationByName",  columnDefinition = "VARCHAR(255)   COMMENT '与主被保人关系'")
    private String relationByName;

    /**
     * 团体意外险id
     */
    @Column(name = "groupInsureId",  columnDefinition = "VARCHAR(255)   COMMENT '团体意外险id'")
    private String groupInsureId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPayAge() {
        return payAge;
    }

    public void setPayAge(String payAge) {
        this.payAge = payAge;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBenefiter() {
        return benefiter;
    }

    public void setBenefiter(String benefiter) {
        this.benefiter = benefiter;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getRelationByName() {
        return relationByName;
    }

    public void setRelationByName(String relationByName) {
        this.relationByName = relationByName;
    }

    public String getGroupInsureId() {
        return groupInsureId;
    }

    public void setGroupInsureId(String groupInsureId) {
        this.groupInsureId = groupInsureId;
    }
}