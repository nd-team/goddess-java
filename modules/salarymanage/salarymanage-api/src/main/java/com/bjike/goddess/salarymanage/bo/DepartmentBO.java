package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentBO extends BaseBO{
    /**
     * 部门
     */
    private String department;

    /**
     * 业务方向子集
     */
    private Set<BusinessDirectionBO> businessDirection;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<BusinessDirectionBO> getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(Set<BusinessDirectionBO> businessDirection) {
        this.businessDirection = businessDirection;
    }
}
