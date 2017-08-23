package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 技能等级情况概览
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverviewSkillLevelTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空",groups = {ADD.class, EDIT.class})
    private String system;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 技能定位-专业（业务范围包含的技能）
     */
    @NotBlank(message = "技能定位-专业（业务范围包含的技能）不能为空",groups = {ADD.class, EDIT.class})
    private String major;

    /**
     * 小项主项技能等级
     */
    @NotBlank(message = "小项主项技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String minorMasterSkillLevel;

    /**
     * 转正技能等级
     */
    @NotBlank(message = "转正技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    @NotBlank(message = "转正时间不能为空",groups = {ADD.class, EDIT.class})
    private String positiveTime;

    /**
     * 获取时间
     */
    @NotBlank(message = "获取时间不能为空",groups = {ADD.class, EDIT.class})
    private String acquisitionTime;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

    /**
     * 主项/小项（是否为主项）
     */
    @NotNull(message = "主项/小项（是否为主项）不能为空",groups = {ADD.class, EDIT.class})
    private Boolean subject;

    /**
     * 小项顺序
     */
    @NotNull(message = "小项顺序不能为空",groups = {ADD.class, EDIT.class})
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

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
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