package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 入职基本信息业务传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 13:55]
 * @Description: [入职基本信息业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryOptionBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 员工编号
     */
    private String employeeID;

    /**
     * 专业
     */
    private String profession;
    /**
     * 学历
     */
    private String education;
    /**
     * 地区
     */
    private String area;
    /**
     * 部门/项目组
     */
    private String department;
    /**
     * 职位 
     */
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
