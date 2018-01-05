package com.bjike.goddess.festival.vo;

/**
 * 节假日工作安排表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HolidayWorkPlanVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 加班原因
     */
    private String overTimeReasion;

    /**
     * 加班名单
     */
    private String overTimeEmper;

    /**
     * 加班时间
     */
    private String overTimeTime;

    /**
     * 加班总人数
     */
    private String overTimeEmperNum;

    /**
     * 加班报酬形式
     */
    private String overTimeSalary;

    /**
     * 安全检查员名单
     */
    private String saferName;

    /**
     * 安全检查员总人数
     */
    private String saferNum;

    /**
     * 应急方案
     */
    private String emergencyPlan;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOverTimeReasion() {
        return overTimeReasion;
    }

    public void setOverTimeReasion(String overTimeReasion) {
        this.overTimeReasion = overTimeReasion;
    }

    public String getOverTimeEmper() {
        return overTimeEmper;
    }

    public void setOverTimeEmper(String overTimeEmper) {
        this.overTimeEmper = overTimeEmper;
    }

    public String getOverTimeTime() {
        return overTimeTime;
    }

    public void setOverTimeTime(String overTimeTime) {
        this.overTimeTime = overTimeTime;
    }

    public String getOverTimeEmperNum() {
        return overTimeEmperNum;
    }

    public void setOverTimeEmperNum(String overTimeEmperNum) {
        this.overTimeEmperNum = overTimeEmperNum;
    }

    public String getOverTimeSalary() {
        return overTimeSalary;
    }

    public void setOverTimeSalary(String overTimeSalary) {
        this.overTimeSalary = overTimeSalary;
    }

    public String getSaferName() {
        return saferName;
    }

    public void setSaferName(String saferName) {
        this.saferName = saferName;
    }

    public String getSaferNum() {
        return saferNum;
    }

    public void setSaferNum(String saferNum) {
        this.saferNum = saferNum;
    }

    public String getEmergencyPlan() {
        return emergencyPlan;
    }

    public void setEmergencyPlan(String emergencyPlan) {
        this.emergencyPlan = emergencyPlan;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


}