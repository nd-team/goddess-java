package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 技能等级情况概览业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverviewSkillLevelBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 体系
     */
    private String system;

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 姓名
     */
    private String name;

    /**
     * 技能定位-专业（业务范围包含的技能）
     */
    private String major;

    /**
     * 小项主项技能等级
     */
    private String minorMasterSkillLevel;

    /**
     * 转正技能等级
     */
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    private LocalDate positiveTime;

    /**
     * 获取时间
     */
    private LocalDate acquisitionTime;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

    /**
     * 主项/小项（是否为主项）
     */
    private Boolean subject;

    /**
     * 小项顺序
     */
    private Integer orderEvent;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinorMasterSkillLevel() {
        return minorMasterSkillLevel;
    }

    public void setMinorMasterSkillLevel(String minorMasterSkillLevel) {
        this.minorMasterSkillLevel = minorMasterSkillLevel;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
    }

    public LocalDate getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(LocalDate positiveTime) {
        this.positiveTime = positiveTime;
    }

    public LocalDate getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(LocalDate acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Integer getPromotedNumber() {
        return promotedNumber;
    }

    public void setPromotedNumber(Integer promotedNumber) {
        this.promotedNumber = promotedNumber;
    }

    public Boolean getSubject() {
        return subject;
    }

    public void setSubject(Boolean subject) {
        this.subject = subject;
    }

    public Integer getOrderEvent() {
        return orderEvent;
    }

    public void setOrderEvent(Integer orderEvent) {
        this.orderEvent = orderEvent;
    }
}