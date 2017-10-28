package com.bjike.goddess.rentutilitiespay.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-20 16:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCollectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门集合
     */
    private List<DepartmentCollectVO> departmentCollectList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentCollectVO> getDepartmentCollectList() {
        return departmentCollectList;
    }

    public void setDepartmentCollectList(List<DepartmentCollectVO> departmentCollectList) {
        this.departmentCollectList = departmentCollectList;
    }
}
