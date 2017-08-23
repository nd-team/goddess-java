package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 体系间对赌表D
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表D ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_systembetd")
public class SystemBetD extends BaseEntity {

    /**
     * 未达标分配体系
     */
    @Column(name = "unmetAllocationSystem", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未达标分配体系'")
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    @Column(name = "unmetAllocation", columnDefinition = "DECIMAL(10,2)   COMMENT '未达标分配'")
    private Double unmetAllocation;
    /**
     * 体系间对赌表C
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "systemBetC_id", columnDefinition = "VARCHAR(36)   COMMENT '体系间对赌表B'")
    private SystemBetC systemBetC;

    public String getUnmetAllocationSystem() {
        return unmetAllocationSystem;
    }

    public void setUnmetAllocationSystem(String unmetAllocationSystem) {
        this.unmetAllocationSystem = unmetAllocationSystem;
    }

    public Double getUnmetAllocation() {
        return unmetAllocation;
    }

    public void setUnmetAllocation(Double unmetAllocation) {
        this.unmetAllocation = unmetAllocation;
    }

    public SystemBetC getSystemBetC() {
        return systemBetC;
    }

    public void setSystemBetC(SystemBetC systemBetC) {
        this.systemBetC = systemBetC;
    }
}