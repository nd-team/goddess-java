package com.bjike.goddess.staffentry.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 员工入职注册
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffentry_staffentryregister")
public class StaffEntryRegister extends BaseEntity {

    /**
     * 所属部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '所属部门'")
    private String department;

    /**
     * 角色列表
     */
    @Column(name = "role",  columnDefinition = "VARCHAR(255)   COMMENT '角色列表'")
    private String role;

    /**
     * 职位
     */
    @Column(name = "position",  columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 工作邮箱
     */
    @Column(name = "workEmail",  columnDefinition = "VARCHAR(255)   COMMENT '工作邮箱'")
    private String workEmail;
    /**
     * 工作邮箱密码
     */
    @Column(name = "workEmailPassword",  columnDefinition = "VARCHAR(255)   COMMENT '工作邮箱密码'")
    private String workEmailPassword;


    /**
     * 用户id
     */
    @Column(name = "userId", nullable = false,unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '用户id'")
    private String userId;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkEmailPassword() {
        return workEmailPassword;
    }

    public void setWorkEmailPassword(String workEmailPassword) {
        this.workEmailPassword = workEmailPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}