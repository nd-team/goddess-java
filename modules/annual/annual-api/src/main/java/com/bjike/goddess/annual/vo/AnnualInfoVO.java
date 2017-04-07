package com.bjike.goddess.annual.vo;

/**
 * 年假信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualInfoVO {
    /**
     * 年度
     */
    private Integer year;

    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 岗位
     */
    private String position;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 工龄
     */
    private String seniority;

    /**
     * 可休年假天数
     */
    private Integer annual;

    /**
     * 剩余年假天数
     */
    private Double surplus;

    /**
     * 是否已休假
     */
    private Boolean already;

    /**
     * 备注
     */
    private String remark;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    public Boolean isAlready() {
        return already;
    }

    public void isAlready(Boolean already) {
        this.already = already;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}