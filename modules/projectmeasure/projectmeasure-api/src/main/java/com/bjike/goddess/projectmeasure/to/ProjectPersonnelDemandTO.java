package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectmeasure.type.PeopleProperty;
import com.sun.prism.shader.FillRoundRect_Color_AlphaTest_Loader;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目人员需求
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectPersonnelDemandTO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "姓名不能为空")
    private String name;

    /**
     * 人员性质
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "人员性质不能为空")
    private PeopleProperty peopleProperty;

    /**
     * 岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String post;

    /**
     * 个人职称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "个人职称不能为空")
    private String personalTitle;

    /**
     * 工作年限
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "工作年限必须是大于等于0的小数")
    private Double yearsOfWork;

    /**
     * 资质
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "资质不能为空")
    private String aptitude;

    /**
     * 项目期间正常人工成本
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "项目期间正常人工成本必须是大于等于0的整数")
    private Integer normalLaborCost;

    /**
     * 项目期间加班人工成本
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "项目期间加班人工成本必须是大于等于0的整数")
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