package com.bjike.goddess.dispatchcar.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 17:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCollectBO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门子集
     */
    List<DepartmentCollectBO> departmentCollect;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentCollectBO> getDepartmentCollect() {
        return departmentCollect;
    }

    public void setDepartmentCollect(List<DepartmentCollectBO> departmentCollect) {
        this.departmentCollect = departmentCollect;
    }
}
