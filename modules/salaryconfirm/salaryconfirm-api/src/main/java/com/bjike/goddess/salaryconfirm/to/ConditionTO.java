package com.bjike.goddess.salaryconfirm.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 汇总分析
 * <p>
 * Created by ike on 17-5-22.
 */
public class ConditionTO extends BaseTO {

    private Integer year;

    private Integer month;

    private String area;

    private String department;

    private String userName;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
