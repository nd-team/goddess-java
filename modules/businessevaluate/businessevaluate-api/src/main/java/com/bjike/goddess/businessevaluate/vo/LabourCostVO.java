package com.bjike.goddess.businessevaluate.vo;

/**
 * 劳动成本表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 劳动成本表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LabourCostVO {

    /**
     * id
     */
    private String id;
    /**
     * 正常工资
     */
    private Double normalSalary;

    /**
     * 加班工资
     */
    private Double overtimeSalary;

    /**
     * 人员租赁费
     */
    private Double staffLease;

    /**
     * 其他支出
     */
    private Double another;

    /**
     * 项目信息id
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getNormalSalary() {
        return normalSalary;
    }

    public void setNormalSalary(Double normalSalary) {
        this.normalSalary = normalSalary;
    }

    public Double getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(Double overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public Double getStaffLease() {
        return staffLease;
    }

    public void setStaffLease(Double staffLease) {
        this.staffLease = staffLease;
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