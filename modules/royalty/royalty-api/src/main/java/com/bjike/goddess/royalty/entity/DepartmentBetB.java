package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbetb")
public class DepartmentBetB extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 目标-部门分配基础权重（%）
     */
    @Column(name = "baseWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标-部门分配基础权重（%）'")
    private Double baseWeight;


    /**
     * 基础得分（部门总得分*目标-部门分配基础权重）
     */
    @Column(name = "basesScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '基础得分（部门总得分*目标-部门分配基础权重）'")
    private Double basesScore;


    /**
     * 制约得分
     */
    @Column(name = "restrictScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '制约得分'")
    private Double restrictScore;

    /**
     * 部门总得分（制约得分+基础得分）
     */
    @Column(name = "departmentTotalScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '部门总得分（制约得分+基础得分）'")
    private Double departmentTotalScore;
    /**
     * 部门对赌表A
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门对赌表A'")
    private DepartmentBetA departmentBetA;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }


    public Double getBasesScore() {
        return basesScore;
    }

    public void setBasesScore(Double basesScore) {
        this.basesScore = basesScore;
    }


    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public DepartmentBetA getDepartmentBetA() {
        return departmentBetA;
    }

    public void setDepartmentBetA(DepartmentBetA departmentBetA) {
        this.departmentBetA = departmentBetA;
    }
}