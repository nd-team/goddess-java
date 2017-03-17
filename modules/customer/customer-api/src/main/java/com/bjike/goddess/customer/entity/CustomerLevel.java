package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;


/**
 * 客户级别
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.868 ]
 * @Description: [ 客户级别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_customerlevel")
public class CustomerLevel extends BaseEntity {

    /**
     * 客户级别名
     */
    @Column(name = "name", nullable = false, unique = true,columnDefinition = "VARCHAR(255)   COMMENT '客户级别名'")
    private String name;

    /**
     * 级别描述
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '级别描述'")
    private String remark;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '客户级别状态'", nullable = false, insertable = false)
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}