package com.bjike.goddess.attendance.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-08 10:08]
 * @Description: [ 地区传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InternalContactsConditionVO implements Serializable {
    /**
     * 当前用户
     */
    private String userName;
    /**
     * 地区
     */
    private String area;

    /**
     * 员工编号
     */
    private String number;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 职位
     */
    private String position;


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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
