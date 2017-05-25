package com.bjike.goddess.coststandard.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 费用标准对比
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:15 ]
 * @Description: [ 费用标准对比 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "coststandard_coststandardcontrast")
public class CostStandardContrast extends BaseEntity {

    /**
     * 标准
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "standard_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '标准'")
    private CostStandard standard;

    /**
     * 资金准备
     */
    @Column(name = "plan", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金准备'")
    private Double plan;

    /**
     * 实际
     */
    @Column(name = "actual", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际'")
    private Double actual;


    public CostStandard getStandard() {
        return standard;
    }

    public void setStandard(CostStandard standard) {
        this.standard = standard;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(Double plan) {
        this.plan = plan;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }
}