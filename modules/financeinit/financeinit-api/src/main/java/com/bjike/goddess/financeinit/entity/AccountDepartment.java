package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 核算部门
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_accountdepartment")
public class AccountDepartment extends BaseEntity {

    /**
     * 核算部门
     */
    @Column(name = "accountDepartment", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '核算部门'")
    private String accountDepartment;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 职员
     */
    @Column(name = "staff", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职员'")
    private String staff;


    public String getAccountDepartment() {
        return accountDepartment;
    }

    public void setAccountDepartment(String accountDepartment) {
        this.accountDepartment = accountDepartment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}