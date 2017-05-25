package com.bjike.goddess.managementpromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 管理等级情况慨览
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:53 ]
 * @Description: [ 管理等级情况慨览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managementpromotion_levelshow")
public class LevelShow extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 岗位
     */
    @Column(name = "job", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String job;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeId", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeId;

    /**
     * 分类
     */
    @Column(name = "classification", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classification;

    /**
     * 管理方向
     */
    @Column(name = "direction", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '管理方向'")
    private String direction;

    /**
     * 转正时间
     */
    @Column(name = "positiveDate", columnDefinition = "DATE   COMMENT '转正时间'")
    private LocalDate positiveDate;

    /**
     * 当前管理等级
     */
    @Column(name = "currentLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当前管理等级'")
    private String currentLevel;

    /**
     * 在管理岗位时间(月)
     */
    @Column(name = "inManageTime", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '在管理岗位时间(月)'")
    private Double inManageTime;

    /**
     * 已晋升次数
     */
    @Column(name = "promotionNum", nullable = false, columnDefinition = "INT(11)   COMMENT '已晋升次数'")
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

    public LocalDate getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(LocalDate positiveDate) {
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