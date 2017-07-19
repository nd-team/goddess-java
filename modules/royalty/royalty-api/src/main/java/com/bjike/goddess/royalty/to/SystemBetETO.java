package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-7-13.
 */
public class SystemBetETO extends BaseTO{
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
    private String systemBetCId;

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

    public String getSystemBetCId() {
        return systemBetCId;
    }

    public void setSystemBetCId(String systemBetCId) {
        this.systemBetCId = systemBetCId;
    }
}
