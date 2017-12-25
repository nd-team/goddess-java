package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 部门间对赌表E业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表E业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetEBO extends BaseBO {
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