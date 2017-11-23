package com.bjike.goddess.lendreimbursement.entity.olddata;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 老系统的报销
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-21 12:03 ]
 * @Description: [ 老系统的报销 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "aj_oa_rbac_user")
public class AjoaUser extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username",   columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 员工编号
     */
    @Column(name = "employeeNumber" , columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNumber;

    /**
     * 密码
     */
    @Column(name = "password",   columnDefinition = "VARCHAR(255)   COMMENT '密码'")
    private String password;

    /**
     * 头像
     */
    @Column(name = "headPath",   columnDefinition = "VARCHAR(255)   COMMENT '头像'")
    private String headPath;

    /**
     * 部门id
     */
    @Column(name = "department_id",   columnDefinition = "VARCHAR(255)   COMMENT '部门id'")
    private String department_id;

    /**
     * 岗位id
     */
    @Column(name = "post_id",   columnDefinition = "VARCHAR(255)   COMMENT '岗位id'")
    private String post_id;

    /**
     * 组id
     */
    @Column(name = "group_id",   columnDefinition = "VARCHAR(255)   COMMENT '组id'")
    private String group_id;

    /**
     * 是否为超级用户-0代表普通用户-代表超级用户
     */
    @Column(name = "superUser", columnDefinition = "INT(2)   COMMENT '是否为超级用户-0代表普通用户-代表超级用户'")
    private int superUser;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "INT(2)   COMMENT '状态'")
    private int status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getSuperUser() {
        return superUser;
    }

    public void setSuperUser(int superUser) {
        this.superUser = superUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}