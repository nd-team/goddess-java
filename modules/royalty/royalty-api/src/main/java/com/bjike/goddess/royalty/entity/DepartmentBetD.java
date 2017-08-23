package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表D
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表D ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbetd")
public class DepartmentBetD extends BaseEntity {

    /**
     * 未达标分配部门
     */
    @Column(name = "unmetAllocationDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未达标分配部门'")
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    @Column(name = "unmetAllocation", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未达标分配'")
    private Double unmetAllocation;


    /**
     * 部门间对赌表C
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetC_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门间对赌表C'")
    private DepartmentBetC departmentBetC;

    public String getUnmetAllocationDepartment() {
        return unmetAllocationDepartment;
    }

    public void setUnmetAllocationDepartment(String unmetAllocationDepartment) {
        this.unmetAllocationDepartment = unmetAllocationDepartment;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public DepartmentBetC getDepartmentBetC() {
        return departmentBetC;
    }

    public void setDepartmentBetC(DepartmentBetC departmentBetC) {
        this.departmentBetC = departmentBetC;
    }
}