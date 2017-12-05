package com.bjike.goddess.salarymanage.vo;

import java.util.List;
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
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private List<DepartmentVO> salaryTestDepartmentSet;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentVO> getSalaryTestDepartmentSet() {
        return salaryTestDepartmentSet;
    }

    public void setSalaryTestDepartmentSet(List<DepartmentVO> salaryTestDepartmentSet) {
        this.salaryTestDepartmentSet = salaryTestDepartmentSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
