package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.PeopleProperty;

/**
 * 项目人员需求业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectPersonnelDemandBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 人员性质
     */
    private PeopleProperty peopleProperty;

    /**
     * 岗位
     */
    private String post;

    /**
     * 个人职称
     */
    private String personalTitle;

    /**
     * 工作年限
     */
    private Double yearsOfWork;

    /**
     * 资质
     */
    private String aptitude;

    /**
     * 项目期间正常人工成本
     */
    private Integer normalLaborCost;

    /**
     * 项目期间加班人工成本
     */
    private Integer overtimeLaborCost;

    /**
     * 人工总成本
     */
    private Integer totalCost;


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

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}