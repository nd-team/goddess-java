package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.contacts.enums.Status;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_commonality")
public class Commonality extends BaseEntity {

    /**
     * 项目组/部门ID
     */
    @Column(name = "department_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目组/部门ID'", unique = true)
    private String departmentId;

    /**
     * 邮箱地址
     */
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮箱地址'")
    private String email;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0   COMMENT '状态'")
    private Status status;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}