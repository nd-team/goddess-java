package com.bjike.goddess.rotation.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 目前岗位情况业务传输对象
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:30 ]
 * @Description: [ 目前岗位情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CurrentPositionBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 转正时间
     */
    private String turnPositiveTime;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 轮岗层级
     */
    private String rotationArrangement;

    /**
     * 获得时间
     */
    private String getTime;

    /**
     * 在岗时长
     */
    private Integer rotationCycle;

    /**
     * 在岗补贴
     */
    private Double subsidy;

    /**
     * 是否应该轮岗
     */
    private Boolean rotation;

    /**
     * 是否延后
     */
    private Boolean delay;

    /**
     * 延后次数
     */
    private Integer delayTimes;

    /**
     * 延后的在岗时长
     */
    private Double delayCycle;

    /**
     * （延后）是否应该轮岗
     */
    private Boolean delayRotation;

    /**
     * 轮岗次数
     */
    private Integer rotationTimes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTurnPositiveTime() {
        return turnPositiveTime;
    }

    public void setTurnPositiveTime(String turnPositiveTime) {
        this.turnPositiveTime = turnPositiveTime;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getRotationArrangement() {
        return rotationArrangement;
    }

    public void setRotationArrangement(String rotationArrangement) {
        this.rotationArrangement = rotationArrangement;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public Integer getRotationCycle() {
        return rotationCycle;
    }

    public void setRotationCycle(Integer rotationCycle) {
        this.rotationCycle = rotationCycle;
    }

    public Double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Double subsidy) {
        this.subsidy = subsidy;
    }

    public Boolean getRotation() {
        return rotation;
    }

    public void setRotation(Boolean rotation) {
        this.rotation = rotation;
    }

    public Boolean getDelay() {
        return delay;
    }

    public void setDelay(Boolean delay) {
        this.delay = delay;
    }

    public Integer getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Integer delayTimes) {
        this.delayTimes = delayTimes;
    }

    public Double getDelayCycle() {
        return delayCycle;
    }

    public void setDelayCycle(Double delayCycle) {
        this.delayCycle = delayCycle;
    }

    public Boolean getDelayRotation() {
        return delayRotation;
    }

    public void setDelayRotation(Boolean delayRotation) {
        this.delayRotation = delayRotation;
    }

    public Integer getRotationTimes() {
        return rotationTimes;
    }

    public void setRotationTimes(Integer rotationTimes) {
        this.rotationTimes = rotationTimes;
    }
}