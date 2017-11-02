package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 利润增减率分析和变动分析
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-01 05:07 ]
 * @Description: [ 利润增减率分析和变动分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_profitformula")
public class ProfitFormula extends BaseEntity {

    /**
     * 分析名
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分析名'")
    private String project;

    /**
     * 说明
     */
    @Column(name = "remark", columnDefinition = "TEXT   COMMENT '说明'")
    private String remark;

    /**
     * 分析类型(0:利润增减率分析,1是变动分析)
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '分析类型'")
    private Integer type;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}