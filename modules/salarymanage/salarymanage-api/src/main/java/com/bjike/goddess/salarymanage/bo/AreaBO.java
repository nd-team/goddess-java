package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaBO extends BaseBO{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private List<DepartmentBO> salaryTestDepartmentSet;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentBO> getSalaryTestDepartmentSet() {
        return salaryTestDepartmentSet;
    }

    public void setSalaryTestDepartmentSet(List<DepartmentBO> salaryTestDepartmentSet) {
        this.salaryTestDepartmentSet = salaryTestDepartmentSet;
    }
}
