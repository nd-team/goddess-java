package com.bjike.goddess.salarymanage.vo;

import com.bjike.goddess.salarymanage.enums.WorkAge;

/**
 * 薪资测算明细表表现层对象
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-16 10:45 ]
 * @Description: [ 薪资测算明细表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryCalculateDetailVO {


    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String userName;

    /**
     * 岗位
     */
    private String position;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 业务方向
     */
    private String businessDirection;

    /**
     * 技能
     */
    private String skill;

    /**
     * 工作年限
     */
    private WorkAge workAge;

    /**
     * 期望
     */
    private Integer expectation;

    /**
     * 智联招聘
     */
    private Integer zhilian;

    /**
     * 中华英才网
     */
    private Integer zhonghua;

    /**
     * 猎聘网
     */
    private Integer lieping;

    /**
     * 前程无忧
     */
    private Integer wuyou;

    /**
     * BOSS聘
     */
    private Integer boss;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public WorkAge getWorkAge() {
        return workAge;
    }

    public void setWorkAge(WorkAge workAge) {
        this.workAge = workAge;
    }

    public Integer getExpectation() {
        return expectation;
    }

    public void setExpectation(Integer expectation) {
        this.expectation = expectation;
    }

    public Integer getZhilian() {
        return zhilian;
    }

    public void setZhilian(Integer zhilian) {
        this.zhilian = zhilian;
    }

    public Integer getZhonghua() {
        return zhonghua;
    }

    public void setZhonghua(Integer zhonghua) {
        this.zhonghua = zhonghua;
    }

    public Integer getLieping() {
        return lieping;
    }

    public void setLieping(Integer lieping) {
        this.lieping = lieping;
    }

    public Integer getWuyou() {
        return wuyou;
    }

    public void setWuyou(Integer wuyou) {
        this.wuyou = wuyou;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }
}