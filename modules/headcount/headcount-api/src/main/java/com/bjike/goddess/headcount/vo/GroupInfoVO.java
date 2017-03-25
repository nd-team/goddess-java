package com.bjike.goddess.headcount.vo;


import javax.persistence.Column;

/**
 * 部门信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-10 09:34 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupInfoVO {
    /**
     * 部门名称
     */
    private String name;

    /**
     * 计划成本数
     */
    private Double plancostnum;

    /**
     * 人工成本计划id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '人工成本计划id'")
    private String planCosts_id;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPlancostnum() {
        return plancostnum;
    }

    public void setPlancostnum(Double plancostnum) {
        this.plancostnum = plancostnum;
    }

    public String getPlanCosts_id() {
        return planCosts_id;
    }

    public void setPlanCosts_id(String planCosts_id) {
        this.planCosts_id = planCosts_id;
    }
}
