package com.bjike.goddess.dataprogress.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 资料收集进度管理主表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DataProgressMainBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 任务下达人
     */
    private String missionRelease;

    /**
     * 计划收集时间
     */
    private String planCollectionTime;

    /**
     * 专业类别
     */
    private String majorKind;

    /**
     * 专业方向
     */
    private String majorDirection;

    /**
     * 技能
     */
    private String skill;

    /**
     * 厂商
     */
    private String manufacturer;

    /**
     * 资料收集进度字表
     */
    private List<DataProgressChildBO> dataProgressChildBOS;

    public List<DataProgressChildBO> getDataProgressChildBOS() {
        return dataProgressChildBOS;
    }

    public void setDataProgressChildBOS(List<DataProgressChildBO> dataProgressChildBOS) {
        this.dataProgressChildBOS = dataProgressChildBOS;
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

    public String getMissionRelease() {
        return missionRelease;
    }

    public void setMissionRelease(String missionRelease) {
        this.missionRelease = missionRelease;
    }

    public String getPlanCollectionTime() {
        return planCollectionTime;
    }

    public void setPlanCollectionTime(String planCollectionTime) {
        this.planCollectionTime = planCollectionTime;
    }

    public String getMajorKind() {
        return majorKind;
    }

    public void setMajorKind(String majorKind) {
        this.majorKind = majorKind;
    }

    public String getMajorDirection() {
        return majorDirection;
    }

    public void setMajorDirection(String majorDirection) {
        this.majorDirection = majorDirection;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}