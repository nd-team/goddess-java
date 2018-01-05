package com.bjike.goddess.businessevaluate.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 需求成本业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 10:50 ]
 * @Description: [ 需求成本业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandCostBO extends BaseBO {

    /**
     * 设备费用
     */
    private Double equipmentSalary;

    /**
     * 配置费用
     */
    private Double configSalary;

    /**
     * 车辆费用
     */
    private Double carSalary;

    /**
     * 其他费用
     */
    private Double another;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    public Double getEquipmentSalary() {
        return equipmentSalary;
    }

    public void setEquipmentSalary(Double equipmentSalary) {
        this.equipmentSalary = equipmentSalary;
    }

    public Double getConfigSalary() {
        return configSalary;
    }

    public void setConfigSalary(Double configSalary) {
        this.configSalary = configSalary;
    }

    public Double getCarSalary() {
        return carSalary;
    }

    public void setCarSalary(Double carSalary) {
        this.carSalary = carSalary;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}