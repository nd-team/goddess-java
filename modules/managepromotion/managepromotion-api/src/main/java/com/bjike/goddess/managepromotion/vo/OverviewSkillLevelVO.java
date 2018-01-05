package com.bjike.goddess.managepromotion.vo;

/**
 * 技能等级情况概览表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverviewSkillLevelVO {

    /**
     * id
     */
    private String id;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 地区
     */
    private String area;

    /**
     * 姓名
     */
    private String name;


    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;
    /**
     * 岗位层级
     */
    private String postHierarchy;
    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 转正时间
     */
    private String positiveTime;
    /**
     * 在职时间（月）
     */
    private String workingTime ;
    /**
     * 技能项
     */
    private String skill;

    /**
     * 技能专业
     */
    private String major;

    /**
     * 技能级别
     */
    private String grade;

    /**
     * 转正技能等级
     */
    private String transferSkillLevel;

    /**
     * 获取时间
     */
    private String acquisitionTime;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
    }

    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Integer getPromotedNumber() {
        return promotedNumber;
    }

    public void setPromotedNumber(Integer promotedNumber) {
        this.promotedNumber = promotedNumber;
    }
}