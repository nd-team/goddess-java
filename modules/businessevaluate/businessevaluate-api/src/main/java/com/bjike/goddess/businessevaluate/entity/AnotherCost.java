package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 其它成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_anothercost")
public class AnotherCost extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String name;

    /**
     * 成本
     */
    @Column(name = "salary", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '成本'")
    private Double salary;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目信息Id'")
    private String projectInfoId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}