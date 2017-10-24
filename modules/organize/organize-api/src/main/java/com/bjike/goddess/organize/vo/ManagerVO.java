package com.bjike.goddess.organize.vo;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-13 11:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ManagerVO {

    /**
     * 预估金额
     */
    private Double money;

    /**
     * 地区
     */
    private int area;

    /**
     * 地区
     */
    private String areas;

    /**
     * 项目组/部门
     */
    private int department;

    /**
     * 项目组/部门
     */
    private String departments;


    /**
     * 内部项目数
     */
    private Long projectNum;

    /**
     * 岗位数
     */
    private Long positionNum;

    /**
     * 岗位无任职人数
     */
    private Long noEmployeesNum;

    /**
     * 兼任数
     */
    private Long partjobNum;

    /**
     * 已有岗位说明书
     */
    private Long jobDescriptionNum;

    /**
     * 无岗位说明书
     */
    private Long noJobNum;

    /**
     * 被调动人数
     */
    private Long moveNum;

    /**
     * 通过（轮岗人数）
     */
    private Long rotationNum;

    /**
     * 待入职人数
     */
    private Long waitEntryNum;

    /**
     * 在职人员数
     */
    private Long entryNum;

    /**
     * 已离职人员数
     */
    private Long dimissionNum;

    /**
     * 待离职人数
     */
    private Long waitDimissionNum;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public Long getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Long projectNum) {
        this.projectNum = projectNum;
    }

    public Long getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(Long positionNum) {
        this.positionNum = positionNum;
    }

    public Long getNoEmployeesNum() {
        return noEmployeesNum;
    }

    public void setNoEmployeesNum(Long noEmployeesNum) {
        this.noEmployeesNum = noEmployeesNum;
    }

    public Long getPartjobNum() {
        return partjobNum;
    }

    public void setPartjobNum(Long partjobNum) {
        this.partjobNum = partjobNum;
    }

    public Long getJobDescriptionNum() {
        return jobDescriptionNum;
    }

    public void setJobDescriptionNum(Long jobDescriptionNum) {
        this.jobDescriptionNum = jobDescriptionNum;
    }

    public Long getNoJobNum() {
        return noJobNum;
    }

    public void setNoJobNum(Long noJobNum) {
        this.noJobNum = noJobNum;
    }

    public Long getMoveNum() {
        return moveNum;
    }

    public void setMoveNum(Long moveNum) {
        this.moveNum = moveNum;
    }

    public Long getRotationNum() {
        return rotationNum;
    }

    public void setRotationNum(Long rotationNum) {
        this.rotationNum = rotationNum;
    }

    public Long getWaitEntryNum() {
        return waitEntryNum;
    }

    public void setWaitEntryNum(Long waitEntryNum) {
        this.waitEntryNum = waitEntryNum;
    }

    public Long getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Long entryNum) {
        this.entryNum = entryNum;
    }

    public Long getDimissionNum() {
        return dimissionNum;
    }

    public void setDimissionNum(Long dimissionNum) {
        this.dimissionNum = dimissionNum;
    }

    public Long getWaitDimissionNum() {
        return waitDimissionNum;
    }

    public void setWaitDimissionNum(Long waitDimissionNum) {
        this.waitDimissionNum = waitDimissionNum;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }
}
