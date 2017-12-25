package com.bjike.goddess.dispatchcar.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 租车费用基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dispatchcar_leasecarcost", uniqueConstraints = {@UniqueConstraint(columnNames = {"area", "project_group"})})
public class LeaseCarCost extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project_group", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String group;

    /**
     * 租车费
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '租车费'")
    private Double cost;

    /**
     * 创建人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createUser;

    /**
     * 修改人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String updateUser;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}