package com.bjike.goddess.staffmove.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 人员调动意愿情况业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveIntendCaseTemplateExcel {

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名")
    private String name;

    /**
     * 岗位
     */
    @ExcelHeader(name="岗位")
    private String position;

    /**
     * 地区
     */
    @ExcelHeader(name="地区")
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name="项目组/部门")
    private String projectGroup;

    /**
     * 业务方向
     */
    @ExcelHeader(name="业务方向")
    private String businessDirection;

    /**
     * 技能
     */
    @ExcelHeader(name="技能")
    private String skill;

    /**
     * 员工编号
     */
    @ExcelHeader(name="员工编号")
    private String employNum;

    /**
     * 籍贯
     */
    @ExcelHeader(name="籍贯")
    private String nativePlace;

    /**
     * 入职时间
     */
    @ExcelHeader(name="入职时间")
    private LocalDate entryTime;

    /**
     * 入职前居住地区
     */
    @ExcelHeader(name="入职前居住地区")
    private String liveArea;

    /**
     * 是否对工作地区有要求
     */
    @ExcelHeader(name="是否对工作地区有要求")
    private String askWorkArea;

    /**
     * 是否服从全国范围内调动安排
     */
    @ExcelHeader(name="是否服从全国范围内调动安排")
    private String obeyPlan;

    /**
     * 服从调动地区
     */
    @ExcelHeader(name="服从调动地区")
    private String obeyStaffArea;

    /**
     * 掌握技能1
     */
    @ExcelHeader(name="掌握技能1")
    private String masterSkillA;

    /**
     * 掌握技能2
     */
    @ExcelHeader(name="掌握技能2")
    private String masterSkillB;

    /**
     * 掌握技能3
     */
    @ExcelHeader(name="掌握技能3")
    private String masterSkillC;

    /**
     * 掌握技能4
     */
    @ExcelHeader(name="掌握技能4")
    private String masterSkillD;

    /**
     * 掌握技能5
     */
    @ExcelHeader(name="掌握技能5")
    private String masterSkillE;

    /**
     * 掌握技能6
     */
    @ExcelHeader(name="掌握技能6")
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

    public String getAskWorkArea() {
        return askWorkArea;
    }

    public void setAskWorkArea(String askWorkArea) {
        this.askWorkArea = askWorkArea;
    }

    public String getObeyPlan() {
        return obeyPlan;
    }

    public void setObeyPlan(String obeyPlan) {
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