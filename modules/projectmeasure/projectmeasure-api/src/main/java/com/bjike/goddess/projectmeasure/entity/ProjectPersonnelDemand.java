package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.PeopleProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目人员需求
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_projectpersonneldemand")
public class ProjectPersonnelDemand extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String name;

    /**
     * 人员性质
     */
    @Column(name = "peopleProperty", nullable = false, columnDefinition = "TINYINT(2) COMMENT '人员性质'")
    private PeopleProperty peopleProperty;

    /**
     * 岗位
     */
    @Column(name = "post", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '岗位'")
    private String post;

    /**
     * 个人职称
     */
    @Column(name = "personalTitle", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '个人职称'")
    private String personalTitle;

    /**
     * 工作年限
     */
    @Column(name = "yearsOfWork", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '工作年限'")
    private Double yearsOfWork;

    /**
     * 资质
     */
    @Column(name = "aptitude", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '资质'")
    private String aptitude;

    /**
     * 项目期间正常人工成本
     */
    @Column(name = "normalLaborCost", nullable = false, columnDefinition = "INT(11) COMMENT '项目期间正常人工成本'")
    private Integer normalLaborCost;

    /**
     * 项目期间加班人工成本
     */
    @Column(name = "overtimeLaborCost", nullable = false, columnDefinition = "INT(11) COMMENT '项目期间加班人工成本'")
    private Integer overtimeLaborCost;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeopleProperty getPeopleProperty() {
        return peopleProperty;
    }

    public void setPeopleProperty(PeopleProperty peopleProperty) {
        this.peopleProperty = peopleProperty;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
    }

    public Double getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(Double yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
    }

    public Integer getNormalLaborCost() {
        return normalLaborCost;
    }

    public void setNormalLaborCost(Integer normalLaborCost) {
        this.normalLaborCost = normalLaborCost;
    }

    public Integer getOvertimeLaborCost() {
        return overtimeLaborCost;
    }

    public void setOvertimeLaborCost(Integer overtimeLaborCost) {
        this.overtimeLaborCost = overtimeLaborCost;
    }
}