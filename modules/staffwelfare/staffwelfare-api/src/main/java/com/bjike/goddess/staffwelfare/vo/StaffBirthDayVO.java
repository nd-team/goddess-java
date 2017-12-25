package com.bjike.goddess.staffwelfare.vo;

/**
 * Created by haikuang on 17-8-17.
 */
public class StaffBirthDayVO {
    /**
     * 月份
     */
    private Integer month;
    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 是否申请离职
     */
    private Boolean ifDimission;


    /**
     * 离职时间
     */
    private String dimissionDate;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getIfDimission() {
        return ifDimission;
    }

    public void setIfDimission(Boolean ifDimission) {
        this.ifDimission = ifDimission;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
        this.dimissionDate = dimissionDate;
    }
}
