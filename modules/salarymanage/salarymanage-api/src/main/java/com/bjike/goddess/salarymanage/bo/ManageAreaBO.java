package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ManageAreaBO extends BaseBO{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    private Set<ManageDepartmentBO> manageDepartmentBOSet;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<ManageDepartmentBO> getManageDepartmentBOSet() {
        return manageDepartmentBOSet;
    }

    public void setManageDepartmentBOSet(Set<ManageDepartmentBO> manageDepartmentBOSet) {
        this.manageDepartmentBOSet = manageDepartmentBOSet;
    }
}
