package com.bjike.goddess.moneyprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目分配
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:30 ]
 * @Description: [ 项目分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyprepare_projectdetails")
public class ProjectDetails extends BaseEntity {

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 比例分配
     */
    @Column(name = "distribution", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例分配'")
    private Double distribution;

    /**
     * 准备金
     */
    @Column(name = "fund", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double fund;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }
}