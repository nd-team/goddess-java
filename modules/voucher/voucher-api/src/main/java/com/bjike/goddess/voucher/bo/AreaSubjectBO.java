package com.bjike.goddess.voucher.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-27 10:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaSubjectBO {
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组集合
     */
    private List<DepartmentSubjectBO> departmentSubjectList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DepartmentSubjectBO> getDepartmentSubjectList() {
        return departmentSubjectList;
    }

    public void setDepartmentSubjectList(List<DepartmentSubjectBO> departmentSubjectList) {
        this.departmentSubjectList = departmentSubjectList;
    }
}
