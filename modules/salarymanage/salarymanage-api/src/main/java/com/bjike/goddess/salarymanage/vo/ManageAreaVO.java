package com.bjike.goddess.salarymanage.vo;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ManageAreaVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private Set<ManageDepartmentVO> manageDepartmentBOSet;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<ManageDepartmentVO> getManageDepartmentBOSet() {
        return manageDepartmentBOSet;
    }

    public void setManageDepartmentBOSet(Set<ManageDepartmentVO> manageDepartmentBOSet) {
        this.manageDepartmentBOSet = manageDepartmentBOSet;
    }
}
