package com.bjike.goddess.dimission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 离职管理面谈
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dimission_interview")
public class Interview extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 岗位层级
     */
    @Column(name = "positionLever", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String positionLever;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 申请离职离职时间
     */
    @Column(name = "applyTime", nullable = false, columnDefinition = "DATE   COMMENT '申请离职离职时间'")
    private LocalDate applyTime;

    /**
     * 正式离职时间
     */
    @Column(name = "dismissTime", nullable = false, columnDefinition = "DATE   COMMENT '正式离职时间'")
    private LocalDate dismissTime;

    /**
     * 是否需项目经理信息离职面谈
     */
    @Column(name = "is_managerInterview", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需项目经理信息离职面谈'", insertable = false)
    private Boolean managerInterview;

    /**
     * 是否需模块负责人离职面谈
     */
    @Column(name = "is_principalInterview", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需模块负责人离职面谈'", insertable = false)
    private Boolean principalInterview;

    /**
     * 是否需福利模块离职面谈
     */
    @Column(name = "is_welfareInterview", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需福利模块离职面谈'", insertable = false)
    private Boolean welfareInterview;

    /**
     * 是否需要总经办离职面谈
     */
    @Column(name = "is_officeInterview", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需要总经办离职面谈'", insertable = false)
    private Boolean officeInterview;


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

    public String getPositionLever() {
        return positionLever;
    }

    public void setPositionLever(String positionLever) {
        this.positionLever = positionLever;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDate applyTime) {
        this.applyTime = applyTime;
    }

    public LocalDate getDismissTime() {
        return dismissTime;
    }

    public void setDismissTime(LocalDate dismissTime) {
        this.dismissTime = dismissTime;
    }

    public Boolean getManagerInterview() {
        return managerInterview;
    }

    public void setManagerInterview(Boolean managerInterview) {
        this.managerInterview = managerInterview;
    }

    public Boolean getPrincipalInterview() {
        return principalInterview;
    }

    public void setPrincipalInterview(Boolean principalInterview) {
        this.principalInterview = principalInterview;
    }

    public Boolean getWelfareInterview() {
        return welfareInterview;
    }

    public void setWelfareInterview(Boolean welfareInterview) {
        this.welfareInterview = welfareInterview;
    }

    public Boolean getOfficeInterview() {
        return officeInterview;
    }

    public void setOfficeInterview(Boolean officeInterview) {
        this.officeInterview = officeInterview;
    }
}