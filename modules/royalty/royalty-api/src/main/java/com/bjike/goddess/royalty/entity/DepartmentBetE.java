package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表E
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表E ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbete")
public class DepartmentBetE extends BaseEntity {

    /**
     * 未达标分配部门
     */
    @Column(name = "unmetAllocationDepartment",  columnDefinition = "VARCHAR(255)   COMMENT '未达标分配部门'")
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    @Column(name = "unmetAllocation",  columnDefinition = "DECIMAL(10,2)   COMMENT '未达标分配'")
    private Double unmetAllocation;


    /**
     * 部门间对赌表D
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetD_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门间对赌表D'")
    private DepartmentBetD departmentBetD;

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

    public DepartmentBetD getDepartmentBetD() {
        return departmentBetD;
    }

    public void setDepartmentBetD(DepartmentBetD departmentBetD) {
        this.departmentBetD = departmentBetD;
    }
}