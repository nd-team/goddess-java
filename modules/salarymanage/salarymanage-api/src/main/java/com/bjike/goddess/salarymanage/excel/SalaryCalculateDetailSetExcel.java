package com.bjike.goddess.salarymanage.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.salarymanage.enums.WorkAge;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-16 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SalaryCalculateDetailSetExcel extends BaseTO{

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名",notNull = true)
    private String userName;

    /**
     * 岗位
     */
    @ExcelHeader(name="岗位",notNull = true)
    private String position;

    /**
     * 地区
     */
    @ExcelHeader(name="地区",notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name="项目组/部门",notNull = true)
    private String department;


    /**
     * 业务方向
     */
    @ExcelHeader(name="业务方向",notNull = true)
    private String businessDirection;

    /**
     * 技能
     */
    @ExcelHeader(name="技能",notNull = true)
    private String skill;

    /**
     * 工作年限
     */
    @ExcelHeader(name="工作年限",notNull = true)
    private WorkAge workAge;

    /**
     * 期望
     */
    @ExcelHeader(name="期望",notNull = true)
    private Integer expectation;

    /**
     * 智联招聘
     */
    @ExcelHeader(name="智联招聘")
    private Integer zhilian;

    /**
     * 中华英才网
     */
    @ExcelHeader(name="中华英才网")
    private Integer zhonghua;

    @ExcelHeader(name="猎聘网")
    private Integer lieping;

    @ExcelHeader(name="前程无忧")
    private Integer wuyou;

    @ExcelHeader(name="BOSS聘")
    private Integer boss;

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
