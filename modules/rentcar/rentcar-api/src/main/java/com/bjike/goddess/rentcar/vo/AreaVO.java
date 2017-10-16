package com.bjike.goddess.rentcar.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-10 18:16]
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
     * 部门
     */
    private List<DepartmentVO> departmentList;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentVO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentVO> departmentList) {
        this.departmentList = departmentList;
    }
}
