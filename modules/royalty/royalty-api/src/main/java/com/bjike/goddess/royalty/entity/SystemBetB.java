package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 体系间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_systembetb")
public class SystemBetB extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 目标-部门分配基础权重（%）
     */
    @Column(name = "baseWeight", columnDefinition = "DECIMAL(10,2)   COMMENT '目标-部门分配基础权重（%）'")
    private Double baseWeight;

    /**
     * 基础得分（分值*目标-部门分配基础权重）
     */
    @Column(name = "basesScore", columnDefinition = "DECIMAL(10,2)   COMMENT '基础得分（分值*目标-部门分配基础权重）'")
    private Double basesScore;
    /**
     * 制约得分
     */
    @Column(name = "restrictScore", columnDefinition = "DECIMAL(10,2)   COMMENT '制约得分'")
    private Double restrictScore;
    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 部门总得分（制约得分+基础得分）
     */
    @Column(name = "departmentTotalScore", columnDefinition = "DECIMAL(10,2)   COMMENT '部门总得分（制约得分+对赌得分）'")
    private Double departmentTotalScore;

    /**
     * 体系间对赌表A
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "systemBetA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '体系间对赌表A'")
    private SystemBetA systemBetA;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getBasesScore() {
        return basesScore;
    }

    public void setBasesScore(Double basesScore) {
        this.basesScore = basesScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public SystemBetA getSystemBetA() {
        return systemBetA;
    }

    public void setSystemBetA(SystemBetA systemBetA) {
        this.systemBetA = systemBetA;
    }
}