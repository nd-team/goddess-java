package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 部门间对赌表F
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表F ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetFTO extends BaseTO {
    /**
     * 未达标分配部门
     */
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    private Double unmetAllocation;

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

}