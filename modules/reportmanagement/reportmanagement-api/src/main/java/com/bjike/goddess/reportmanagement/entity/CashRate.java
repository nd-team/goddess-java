package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 现金流量比率表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 05:15 ]
 * @Description: [ 现金流量比率表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashrate")
public class CashRate extends BaseEntity {

    /**
     * 现金流量ID
     */
    @Column(name = "projectId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '现金流量ID'")
    private String projectId;

    /**
     * 比率
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比率'")
    private Double rate;

    /**
     * 对应的公式
     */
    @Column(name = "formula", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对应的公式'")
    private String formula;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}