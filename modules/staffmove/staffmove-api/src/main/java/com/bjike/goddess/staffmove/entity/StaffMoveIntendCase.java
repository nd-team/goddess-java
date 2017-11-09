package com.bjike.goddess.staffmove.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 人员调动意愿情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmove_staffmoveintendcase")
public class StaffMoveIntendCase extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 岗位
     */
    @Column(name = "position",  columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String projectGroup;

    /**
     * 业务方向
     */
    @Column(name = "businessDirection",  columnDefinition = "VARCHAR(255)   COMMENT '业务方向'")
    private String businessDirection;

    /**
     * 技能
     */
    @Column(name = "skill",  columnDefinition = "VARCHAR(255)   COMMENT '技能'")
    private String skill;

    /**
     * 员工编号
     */
    @Column(name = "employNum",  columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employNum;

    /**
     * 籍贯
     */
    @Column(name = "nativePlace",  columnDefinition = "VARCHAR(255)   COMMENT '籍贯'")
    private String nativePlace;

    /**
     * 入职时间
     */
    @Column(name = "entryTime",  columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate entryTime;

    /**
     * 入职前居住地区
     */
    @Column(name = "liveArea",  columnDefinition = "VARCHAR(255)   COMMENT '入职前居住地区'")
    private String liveArea;

    /**
     * 是否对工作地区有要求
     */
    @Column(name = "is_askWorkArea",  columnDefinition = "TINYINT(1)  COMMENT '是否对工作地区有要求'")
    private Boolean askWorkArea;

    /**
     * 是否服从全国范围内调动安排
     */
    @Column(name = "is_obeyPlan",  columnDefinition = "TINYINT(1)  COMMENT '是否服从全国范围内调动安排'")
    private Boolean obeyPlan;

    /**
     * 服从调动地区
     */
    @Column(name = "obeyStaffArea",  columnDefinition = "VARCHAR(255)   COMMENT '服从调动地区'")
    private String obeyStaffArea;

    /**
     * 掌握技能1
     */
    @Column(name = "masterSkillA",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能1'")
    private String masterSkillA;

    /**
     * 掌握技能2
     */
    @Column(name = "masterSkillB",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能2'")
    private String masterSkillB;

    /**
     * 掌握技能3
     */
    @Column(name = "masterSkillC",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能3'")
    private String masterSkillC;

    /**
     * 掌握技能4
     */
    @Column(name = "masterSkillD",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能4'")
    private String masterSkillD;

    /**
     * 掌握技能5
     */
    @Column(name = "masterSkillE",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能5'")
    private String masterSkillE;

    /**
     * 掌握技能6
     */
    @Column(name = "masterSkillF",  columnDefinition = "VARCHAR(255)   COMMENT '掌握技能6'")
    private String masterSkillF;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEmployNum() {
        return employNum;
    }

    public void setEmployNum(String employNum) {
        this.employNum = employNum;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public String getLiveArea() {
        return liveArea;
    }

    public void setLiveArea(String liveArea) {
        this.liveArea = liveArea;
    }

    public Boolean getAskWorkArea() {
        return askWorkArea;
    }

    public void setAskWorkArea(Boolean askWorkArea) {
        this.askWorkArea = askWorkArea;
    }

    public Boolean getObeyPlan() {
        return obeyPlan;
    }

    public void setObeyPlan(Boolean obeyPlan) {
        this.obeyPlan = obeyPlan;
    }

    public String getObeyStaffArea() {
        return obeyStaffArea;
    }

    public void setObeyStaffArea(String obeyStaffArea) {
        this.obeyStaffArea = obeyStaffArea;
    }

    public String getMasterSkillA() {
        return masterSkillA;
    }

    public void setMasterSkillA(String masterSkillA) {
        this.masterSkillA = masterSkillA;
    }

    public String getMasterSkillB() {
        return masterSkillB;
    }

    public void setMasterSkillB(String masterSkillB) {
        this.masterSkillB = masterSkillB;
    }

    public String getMasterSkillC() {
        return masterSkillC;
    }

    public void setMasterSkillC(String masterSkillC) {
        this.masterSkillC = masterSkillC;
    }

    public String getMasterSkillD() {
        return masterSkillD;
    }

    public void setMasterSkillD(String masterSkillD) {
        this.masterSkillD = masterSkillD;
    }

    public String getMasterSkillE() {
        return masterSkillE;
    }

    public void setMasterSkillE(String masterSkillE) {
        this.masterSkillE = masterSkillE;
    }

    public String getMasterSkillF() {
        return masterSkillF;
    }

    public void setMasterSkillF(String masterSkillF) {
        this.masterSkillF = masterSkillF;
    }
}