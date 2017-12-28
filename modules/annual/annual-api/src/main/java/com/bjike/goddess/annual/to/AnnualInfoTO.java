package com.bjike.goddess.annual.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 年假信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualInfoTO extends BaseTO {

    /**
     * 年度
     */
    @NotNull(message = "年度不能为空", groups = { ADD.class})
    private Integer year;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = { ADD.class})
    private String username;

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空", groups = { ADD.class})
    private String area;

    /**
     * 岗位
     */
    @NotNull(message = "岗位不能为空", groups = { ADD.class})
    private String position;

    /**
     * 项目组/部门
     */
    @NotNull(message = "项目组不能为空", groups = { ADD.class})
    private String department;

    /**
     * 岗位层级
     */
    @NotNull(message = "岗位层级不能为空", groups = { ADD.class})
    private String arrangement;

    /**
     * 入职时间
     */
    @NotNull(message = "入职时间不能为空", groups = { ADD.class})
    private String entryTime;

    /**
     * 工龄
     */
    @NotNull(message = "工龄不能为空", groups = { ADD.class})
    private String seniority;

    /**
     * 可休年假天数
     */
    @NotNull(message = "可休年假天数不能为空", groups = { ADD.class})
    private Integer annual;

    /**
     * 剩余年假天数
     */
    @NotNull(message = "剩余年假天数不能为空", groups = { ADD.class})
    private Double surplus;

    /**
     * 是否已休假
     */
    @NotNull(message = "是否已休假不能为空", groups = { ADD.class})
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

    public Boolean getAlready() {
        return already;
    }

    public void setAlready(Boolean already) {
        this.already = already;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}