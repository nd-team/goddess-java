package com.bjike.goddess.dispatchcar.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 17:19]
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
     * 部门子集
     */
    List<DepartmentCollectVO> departmentCollect;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentCollectVO> getDepartmentCollect() {
        return departmentCollect;
    }

    public void setDepartmentCollect(List<DepartmentCollectVO> departmentCollect) {
        this.departmentCollect = departmentCollect;
    }
}
