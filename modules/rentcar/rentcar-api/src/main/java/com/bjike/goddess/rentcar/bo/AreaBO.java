package com.bjike.goddess.rentcar.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaBO implements Serializable{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private List<DepartmentBO> departmentList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentBO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentBO> departmentList) {
        this.departmentList = departmentList;
    }
}
