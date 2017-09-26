package com.bjike.goddess.salarymanage.vo;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentVO {

    /**
     * 部门
     */
    private String department;

    /**
     * 业务方向子集
     */
    private Set<BusinessDirectionVO> businessDirection;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<BusinessDirectionVO> getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(Set<BusinessDirectionVO> businessDirection) {
        this.businessDirection = businessDirection;
    }
}
