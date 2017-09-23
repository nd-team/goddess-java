package com.bjike.goddess.salarymanage.vo;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private Set<DepartmentVO> salaryTestDepartmentSet;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<DepartmentVO> getSalaryTestDepartmentSet() {
        return salaryTestDepartmentSet;
    }

    public void setSalaryTestDepartmentSet(Set<DepartmentVO> salaryTestDepartmentSet) {
        this.salaryTestDepartmentSet = salaryTestDepartmentSet;
    }
}
