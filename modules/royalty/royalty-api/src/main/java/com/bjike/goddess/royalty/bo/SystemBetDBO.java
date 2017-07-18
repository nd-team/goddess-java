package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.royalty.entity.SystemBetC;

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
public class SystemBetDBO extends BaseBO {

    /**
     * 未达标分配体系
     */
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;
    /**
     * 体系间对赌表C
     */
    private SystemBetCBO systemBetCBO;



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

    public SystemBetCBO getSystemBetCBO() {
        return systemBetCBO;
    }

    public void setSystemBetCBO(SystemBetCBO systemBetCBO) {
        this.systemBetCBO = systemBetCBO;
    }
}