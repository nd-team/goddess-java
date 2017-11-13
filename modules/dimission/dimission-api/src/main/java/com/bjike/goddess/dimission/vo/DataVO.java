package com.bjike.goddess.dimission.vo;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-11-11 16:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DataVO extends BaseTO {

    /**
     * ID
     */
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String department;

    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 岗位
     */
    private String position;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 学历
     */
    private String education;

    /**
     * 在司工龄(月)
     */
    private String seniority;

//    /**
//     * 私人邮箱
//     */
//    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 入职时间
     */
    private String entryTime;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
