package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 人工成本计划子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:57 ]
 * @Description: [ 人工成本计划子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_son")
public class Son extends BaseEntity {

    /**
     * 薪资区间
     */
    @Column(name = "sal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '薪资区间'")
    private String sal;

    /**
     * 人工成本计划信息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expendPlan_id", columnDefinition = "VARCHAR(36)   COMMENT '人工成本计划信息'")
    private ExpendPlan expendPlan;

//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "son")
//    private List<ExpendPlanSonDetail> details = new ArrayList<>();
//
//    public List<ExpendPlanSonDetail> getDetails() {
//        return details;
//    }
//
//    public void setDetails(List<ExpendPlanSonDetail> details) {
//        this.details = details;
//    }

    public ExpendPlan getExpendPlan() {
        return expendPlan;
    }

    public void setExpendPlan(ExpendPlan expendPlan) {
        this.expendPlan = expendPlan;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }
}