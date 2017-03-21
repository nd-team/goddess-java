package com.bjike.goddess.headcount.entity.plancost;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * 部门信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 10:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "headcount_group_info")
public class GroupInfo extends BaseEntity{
    /**
     * 部门名称
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(255) COMMENT '部门名称'")
    private String name;

    /**
     * 计划成本数
     */
    @Range(min = 0)
    @Column(columnDefinition = "DECIMAL(6,2) COMMENT '计划成本数'")
    private Double plancostnum;

    /**
     * 人工成本计划id
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(36) COMMENT '人工成本计划id'")
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
