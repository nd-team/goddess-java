package com.bjike.goddess.managementpromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 管理等级情况慨览业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LevelShowBO extends BaseBO {

    /**
     * 体系
     */
    private String system;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String job;

    /**
     * 姓名
     */
    private String name;

    /**
     * 分类
     */
    private String classification;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 管理方向
     */
    private String direction;

    /**
     * 转正时间
     */
    private String positiveDate;

    /**
     * 当前管理等级
     */
    private String currentLevel;

    /**
     * 在管理岗位时间(月)
     */
    private Double inManageTime;

    /**
     * 已晋升次数
     */
    private Integer promotionNum;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(String positiveDate) {
        this.positiveDate = positiveDate;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Double getInManageTime() {
        return inManageTime;
    }

    public void setInManageTime(Double inManageTime) {
        this.inManageTime = inManageTime;
    }

    public Integer getPromotionNum() {
        return promotionNum;
    }

    public void setPromotionNum(Integer promotionNum) {
        this.promotionNum = promotionNum;
    }
}