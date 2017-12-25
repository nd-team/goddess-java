package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 登记链接员工入职注册数据业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 登记链接员工入职注册数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LinkDateStaffEntryBO extends BaseBO {

    /**
     * 员工编号
     */
    private String empNumber;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 所属部门/项目组
     */
    private String department;
    /**
     * 入职日期
     */
    private String entryDate;
    /**
     * 地区
     */
    private String area;

    /**
     * 职位
     */
    private String position;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}