package com.bjike.goddess.rentutilitiespay.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-20 16:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCollectBO implements Serializable{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门集合
     */
    private List<DepartmentCollectBO> departmentCollectList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentCollectBO> getDepartmentCollectList() {
        return departmentCollectList;
    }

    public void setDepartmentCollectList(List<DepartmentCollectBO> departmentCollectList) {
        this.departmentCollectList = departmentCollectList;
    }
}
